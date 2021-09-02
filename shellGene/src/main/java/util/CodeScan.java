package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import entity.Method;
import entity.Param;
import entity.TemplateVars;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.MemberValue;

public class CodeScan {

    public static void dirScan(String path) throws IOException {
        File file = new File(path);
        List<File> files = FileSearchUtils.searchByFileDuff(file, "class");
        for (File f : files){
            // 将注解扫描返回的信息提交给java生成方法
            TemplateVars templateVars = getInfo(new FileInputStream(f));
            FileGenerator.javaGene(templateVars);
        }
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
                } else if (annotation.getTypeName().equals("io.swagger.annotations.ApiModel")) { // 是否还有其他的方法？
                    flag = 2;
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
                    System.out.println("~~~~~~~~~~~~~~~~" + methodInfo.getName() + "~~~~~~~~~~~");
                    for(int i = 0; i < attrName.size(); ++i) {
                        Param param = new Param();
                        String name = attrName.getConstPool().getUtf8Info(ByteArray.readU16bit(attrName.get(), i * 4 + 1));
                        param.setName(name);
                        // 多个参数的情况下，会出现一些特殊情况
                        // 类中重复出现的参数，只要参数类型和参数名相同，编译时会对其进行优化，即使他们在逻辑上不相同
//                        while(i > 0 && attrName.getConstPool().getUtf8Info(t-i).equals(params.get(params.size()-i).getName())) {
//                            t--;
//                        }
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
                        System.out.println(type);
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
//        else if(flag == 2) {
//            System.out.println("-------------------------------------------------------------");
//            System.out.println("类的名称：" + cls.getName());
//            //获取属性
//            List<FieldInfo> fields = cls.getFields();
//            for (FieldInfo field : fields) {
//                //获取属性的Runtime注解
//                AnnotationsAttribute attribute1 = (AnnotationsAttribute) field.getAttribute(AnnotationsAttribute.visibleTag);
//                if (attribute1 != null) {
//                    Annotation[] annotations = attribute1.getAnnotations();
//                    for (Annotation annotation : annotations) {
//                        System.out.println("属性的名称：" + field.getDescriptor());
//                        System.out.println("属性的类型： " + annotation.getTypeName());
//                    }
//                }
//            }
//        }
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
