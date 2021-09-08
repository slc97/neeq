package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.Method;
import entity.Param;
import entity.TemplateVars;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.MemberValue;

public class CodeScan {

    private static List<TemplateVars> entityList = new ArrayList<>();

    private static final String[] usualTypes = {"Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character","String"};

    public static List<TemplateVars> dirScan(String path) throws IOException {
        File file = new File(path);
        List<File> files = FileSearchUtils.searchByFileDuff(file, "class");
        List<TemplateVars> templateVarsList = new ArrayList<>();
        // 保存扫描的属性信息
        for (File f : files){
            TemplateVars templateVars = getInfo(new FileInputStream(f));
            if(templateVars.isControllerFlag()) {
                templateVarsList.add(templateVars);
            }
            else if(templateVars.isEntityFlag()) {
                entityList.add(templateVars);
            }
        }

        // 将templateVarsList中的pojo类替换为基本数据类型封装类
        for(int i = 0; i < templateVarsList.size(); ++i) {
            TemplateVars templateVars = templateVarsList.get(i);
            List<Method> methods = templateVars.getMethods();
            for(int j = 0; j < methods.size(); ++j) {
                Method method = methods.get(j);
                List<Param> params = method.getParams();
                if(params == null) continue;
                for (int k = 0; k < params.size(); k++) {
                    Param param = params.get(k);
                    if (!Arrays.asList(usualTypes).contains(param.getType())) {
                        for (TemplateVars entity : entityList) {
                            if (entity.getEntity().equals(param.getType())) {
                                params.remove(k);
                                params.addAll(k, entity.getFields());
                                method.setParams(params);
                                methods.set(j,method);
                                templateVars.setMethods(methods);
                                templateVarsList.set(i, templateVars);
                            }
                        }
                    }
                }
            }
        }
        return templateVarsList;
    }

    public static TemplateVars getInfo(InputStream inputStream) throws IOException {
        // 返回类信息
        TemplateVars templateVars = new TemplateVars();

        DataInputStream dis = new DataInputStream(new BufferedInputStream(inputStream));
        ClassFile cls = new ClassFile(dis);

        // 标记是否是controller（1）或者entity（2）
        int flag = 0;

        // 获取类层级的Runtime注解，用来判断controller和entity
        AnnotationsAttribute attribute = (AnnotationsAttribute) cls.getAttribute(AnnotationsAttribute.visibleTag);
        Annotation[] classAnnotations = null;
        if (attribute != null) {
            classAnnotations = attribute.getAnnotations();
            for(Annotation annotation : classAnnotations) {
                if (annotation.getTypeName().equals("org.springframework.web.bind.annotation.RestController")
                        || annotation.getTypeName().equals("org.springframework.stereotype.Controller")) {
                    flag = 1;
                    templateVars.setControllerFlag(true);
                } else if (annotation.getTypeName().equals("io.swagger.annotations.ApiModel")) { // 是否还有其他的方法？
                    flag = 2;
                    templateVars.setEntityFlag(true);
                }
            }
        }

        // controller类
        if(flag == 1) {
            // 获取className名称
            templateVars.setEntityController(cls.getName());

            // 获取类中的url
            for(Annotation annotation : classAnnotations) {
                if(annotation.getTypeName().equals("org.springframework.web.bind.annotation.RequestMapping")) {
                    String url = annotation.getMemberValue("value").toString();
                    templateVars.setClsUrl(InfoProcessUtil.urlProcess(url));
                }
            }

            // 获取接口方法
            List<MethodInfo> methodInfos = cls.getMethods();    // 通过javaassist获取编译后的method信息
            List<Method> methods = new ArrayList<>();           // 创建method列表，封装到templatevars中
            for(MethodInfo methodInfo : methodInfos) {
                // 排除默认构造方法
                if(methodInfo.getName().equals("<init>")) {
                    continue;
                }
                // 方法层级
                Method method = new Method();
                method.setMethodName(methodInfo.getName());

                AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute)methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
                if(annotationsAttribute != null) {
                    // 标记接口方法
                    boolean flagMethod = false;

                    // 获取接口的注解信息
                    Annotation[] methodAnnotations = annotationsAttribute.getAnnotations();
                    for(Annotation annotation : methodAnnotations) {
                        if(annotation.getTypeName().equals("io.swagger.annotations.ApiOperation")) {
                            // 保存方法层级的描述
                            method.setMethodDoc(annotation.getMemberValue("value").toString());
                        } else if(annotation.getTypeName().equals("org.springframework.web.bind.annotation.RequestMapping")) {
                            // 保存方法层级的url
                            method.setUrl(InfoProcessUtil.urlProcess(annotation.getMemberValue("value").toString()));

                            // 保存接口的Http请求方式
                            MemberValue requestMethod = annotation.getMemberValue("method");
                            if(requestMethod == null) {
                                method.setRequestMethod("post");
                            }
                            else {
                                method.setRequestMethod(InfoProcessUtil.requestProcess(requestMethod.toString()));
                            }
                        }
                    }
                }
                // 获取接口的参数信息
                MethodParametersAttribute attrName = (MethodParametersAttribute)methodInfo.getAttribute(MethodParametersAttribute.tag);
                if(attrName != null ) {
                    List<Param> params = new ArrayList<>();
//                    System.out.println("~~~~~~~~~~~~~~~~" + methodInfo.getName() + "~~~~~~~~~~~");
                    for(int i = 0; i < attrName.size(); ++i) {
                        Param param = new Param();
                        String name = attrName.getConstPool().getUtf8Info(ByteArray.readU16bit(attrName.get(), i * 4 + 1));
                        param.setName(name);
                        // 多个参数的情况下，会出现一些特殊情况
                        // 类中重复出现的参数，只要参数类型和参数名相同，编译时会对其进行优化，即使他们在逻辑上不相同
                        int pos = 0;
                        while(pos < attrName.getConstPool().getSize()){
                            try{
                                if(attrName.getConstPool().getUtf8Info(pos++).equals(methodInfo.getName())) {
                                    break;
                                }
                            }catch (Exception e) {
                            };
                        }
                        // 所有参数都在类中重复出现时，不存在(Ltype;type;...type;)V
                        String typeInfo = attrName.getConstPool().getUtf8Info(pos);
                        if(!typeInfo.contains("(L")) {
                            pos = 0;
                            while(pos < attrName.getConstPool().getSize()){
                                try{
                                    if(attrName.getConstPool().getUtf8Info(pos++).equals(name)) {
                                        typeInfo = attribute.getConstPool().getUtf8Info(pos);
                                        break;
                                    }
                                }catch (Exception e) {
                                };
                            }
                        }
                        String type = InfoProcessUtil.paramTypeProcess(attrName.getConstPool().getUtf8Info(pos))[i];
                        param.setType(type);
                        params.add(param);
                    }
                    method.setParams(params);
                }
                // 当此方法被controller或者restcontroller标记
                methods.add(method);
            }
            templateVars.setMethods(methods);
        }
        // 实体类
        else if(flag == 2) {
            templateVars.setEntity(cls.getName());
            // 获取属性
            List<FieldInfo> fields = cls.getFields();
            List<Param> params = new ArrayList<>();
            for (FieldInfo field : fields) {
                Param param = new Param();
                param.setName(field.getName());
                String protoType = field.getDescriptor();
                if(protoType.equals("B")) {
                    protoType = "Byte";
                } else if(protoType.equals("S")) {
                    protoType = "Short";
                } else if(protoType.equals("I")) {
                    protoType = "Integer";
                } else if(protoType.equals("J")) {
                    protoType = "Long";
                } else if(protoType.equals("F")) {
                    protoType = "Float";
                } else if(protoType.equals("D")) {
                    protoType = "Double";
                } else if(protoType.equals("C")) {
                    protoType = "Character";
                } else if(protoType.equals("Z")) {
                    protoType = "Boolean";
                } else { // 可能会出现嵌套pojo，建议使用json发送
                    protoType = InfoProcessUtil.paramTypeProcess(protoType)[0];
                }
                param.setType(protoType);
                params.add(param);
            }
            templateVars.setFields(params);
        }
        return templateVars;
    }

    public static void urlScan(String url) {
        try {
            ClassPool pool = ClassPool.getDefault();
            ClassPath cp = new URLClassPath("127.0.0.1", 8083, "", "com.neeq.tradingDemo.controller.");
            pool.insertClassPath(cp);
            CtClass cc = null;
            cc = pool.get("DealController");
            CtMethod cm = cc.getDeclaredMethod("getDealById");
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            System.out.println(cm.getParameterTypes().length);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

}
