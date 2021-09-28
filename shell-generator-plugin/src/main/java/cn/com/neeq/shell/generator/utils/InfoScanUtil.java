package cn.com.neeq.shell.generator.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import cn.com.neeq.shell.generator.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 信息扫描工具类
 *
 * @author 宋立成
 * @date 2021-08-30 09:55
 */
@Slf4j
public class InfoScanUtil {

    /**
     * 获取类信息
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<TemplateClazz> getInfo(String path) {
        List<TemplateClazz> templateClazzList = new ArrayList<>();
        List<TemplateClazz> entityList = new ArrayList<>();

        List<String> files = FileSearchUtil.getClassNames(FileSearchUtil.scanCls(path));
        for (String f : files) {
            ClassPool pool = ClassPool.getDefault();

            // 插件不能通过反射获取注解
            // 使用javassist创建CtClass类，用于创建
            CtClass cls = null;
            try {
                cls = pool.get(f);
            } catch (NotFoundException e) {
                log.info(e + ": error when creating class by javassist.");
            }

            // 反射创建Class类
            Class clazz = null;
            try {
                clazz = Class.forName(f);
            }  catch (ClassNotFoundException e) {
                log.info(e + ": error when creating class by Reflection.");
            }

            TemplateClazz templateClazz = markClass(cls);
            if(templateClazz.isControllerFlag()) {
                templateClazzList.add(getControllerInfo(clazz, cls, templateClazz));
            } else if(templateClazz.isEntityFlag()) {
                entityList.add(getEntityInfo(clazz, cls, templateClazz));
            }
        }
        return pojoToBase(templateClazzList, entityList);
    }


    /**
     * 实体类转基本数据类型
     *
     * @param templateClazzList
     * @param entityList
     * @return
     */
    public static List<TemplateClazz> pojoToBase(List<TemplateClazz> templateClazzList, List<TemplateClazz> entityList) {
        for(int i = 0; i < templateClazzList.size(); ++i) {
            TemplateClazz templateClazz = templateClazzList.get(i);
            List<TemplateMethod> templateMethods = templateClazz.getMethods();
            if(templateMethods != null) {
                for (int j = 0; j < templateMethods.size(); ++j) {
                    TemplateMethod templateMethod = templateMethods.get(j);
                    List<TemplateParam> templateParams = templateMethod.getTemplateParams();
                    for (int k = 0; k < templateParams.size(); ++k) {
                        TemplateParam templateParam = templateParams.get(k);
                        String type = templateParam.getType();
                        if (type.startsWith("java.lang")) {
                            templateParam.setType(type.substring(10));
                            templateParams.set(k, templateParam);
                        } else {
                            for (TemplateClazz entity : entityList) {
                                if (entity.getEntity().equals(templateParam.getType())) {
                                    templateParams.remove(k);
                                    templateParams.addAll(k, entity.getFields());
                                }
                            }
                        }
                        templateMethod.setTemplateParams(templateParams);
                    }
                    templateMethods.set(j, templateMethod);
                    templateClazz.setMethods(templateMethods);
                }
            }
            templateClazzList.set(i, templateClazz);
        }
        return templateClazzList;
    }

    /**
     * 扫描class，将Controller和Entity标记出来
     *
     * @param clazz
     * @return
     * @throws IOException
     */
    public static TemplateClazz markClass(CtClass clazz) {
        TemplateClazz templateClazz = new TemplateClazz();

        Controller controller = null;
        RestController restController = null;
        ApiModel apiModel = null;
        try {
            controller = (Controller) clazz.getAnnotation(Controller.class);
            restController = (RestController) clazz.getAnnotation(RestController.class);
            apiModel = (ApiModel) clazz.getAnnotation(ApiModel.class);
        } catch (ClassNotFoundException e) {
            log.info(e+": error when mark class as controller or entity.");
        }
        if(controller != null || restController != null) {
            templateClazz.setControllerFlag(true);
        } else if(apiModel != null) {
            templateClazz.setEntityFlag(true);
        }
        return templateClazz;
    }

    /**
     * 获取Controller信息
     *
     * @param clazz
     * @param templateClazz
     * @return
     */
    public static TemplateClazz getControllerInfo(Class clazz, CtClass cls, TemplateClazz templateClazz) {
        // 获取类名称
        templateClazz.setEntityController(clazz.getName());
        // 获取类中的url
        RequestMapping requestMapping = null;
        try {
            requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
        } catch (ClassNotFoundException e) {
            log.info(e+"");
        }
        if(requestMapping != null) {
            templateClazz.setClsUrl(InfoProcessUtil.annoProcess(Arrays.toString(requestMapping.value())));
        }

        // ----获取接口方法----

        // javassist获取方法
        CtMethod[] ctMethods = cls.getDeclaredMethods();

        // 反射获取方法
        Method[] methods = clazz.getDeclaredMethods();
        List<TemplateMethod> templateMethods = new ArrayList<>();   // 创建method列表，封装到templatevars中
        for (Method method : methods) {
            for(CtMethod ctMethod : ctMethods) {
                if(ctMethod.getName().equals(method.getName())) {
                    TemplateMethod templateMethod = getMehtodInfo(method, ctMethod);
                    templateMethods.add(templateMethod);
                }
            }
        }
        templateClazz.setMethods(templateMethods);
        return templateClazz;
    }

    /**
     * 获取方法信息
     *
     * @param method
     * @return
     */
    public static TemplateMethod getMehtodInfo(Method method, CtMethod ctMethod) {
        TemplateMethod templateMethod = new TemplateMethod();
        templateMethod.setMethodName(method.getName());

        ApiOperation apiOperation = null;
        RequestMapping requestMapping = null;
        try {
            apiOperation = (ApiOperation) ctMethod.getAnnotation(ApiOperation.class);
            requestMapping = (RequestMapping) ctMethod.getAnnotation(RequestMapping.class);
        } catch (ClassNotFoundException e) {
            log.info("Annotation not found: " + e);
        }
        if(apiOperation != null) {
            templateMethod.setMethodDoc(InfoProcessUtil.annoProcess(Arrays.toString(new String[]{apiOperation.value()})));
        }
        if(requestMapping != null) {
            templateMethod.setUrl(InfoProcessUtil.annoProcess(Arrays.toString(requestMapping.value())));
            String requestMethod = InfoProcessUtil.annoProcess(Arrays.toString(requestMapping.method())).toLowerCase();
            templateMethod.setRequestMethod(requestMethod.equals("") ? "post" : requestMethod);
        }

        // 获取接口的参数信息
        Parameter[] parameters = method.getParameters();
        List<TemplateParam> templateParams = new ArrayList<>();
        for (Parameter parameter : parameters) {
            templateParams.add(getParamInfo(parameter));
        }
        templateMethod.setTemplateParams(templateParams);
        return templateMethod;
    }

    /**
     * 获取参数信息
     *
     * @param parameter
     * @return
     */
    public static TemplateParam getParamInfo(Parameter parameter) {
        TemplateParam templateParam = new TemplateParam();
        if(parameter.getType().isPrimitive()) {
            String[] strs = parameter.getType().getName().split("\\.");
            templateParam.setType(strs[strs.length-1]);
        } else {
            templateParam.setType(parameter.getType().getName());
        }
        templateParam.setName(parameter.getName());
        return templateParam;
    }

    /**
     * 获取Entity信息
     *
     * @param clazz
     * @param templateClazz
     * @return
     */
    public static TemplateClazz getEntityInfo(Class clazz, CtClass cls, TemplateClazz templateClazz) {
        templateClazz.setEntity(clazz.getName());
        // 获取属性
        Field[] fields = clazz.getDeclaredFields();
        List<TemplateParam> templateParams = new ArrayList<>();
        for (Field field : fields) {
            TemplateParam templateParam = new TemplateParam();
            templateParam.setName(field.getName());
            templateParam.setType(field.getType().getName());
            templateParams.add(templateParam);
        }
        templateClazz.setFields(templateParams);
        return templateClazz;
    }
}
