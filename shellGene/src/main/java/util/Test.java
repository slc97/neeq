//package util;
//
//import entity.TemplateVars;
//import javassist.*;
//import javassist.bytecode.ClassFile;
//import javassist.bytecode.CodeAttribute;
//import javassist.bytecode.LocalVariableAttribute;
//import javassist.bytecode.MethodInfo;
//
//import java.io.*;
//import java.util.List;
//
///**
// * @author 宋立成
// * @date 2021-09-01 11:06
// */
//public class Test {
//
//    public static void main(String[] args) throws IOException, NotFoundException {
////        String path = "C:\\Users\\32858\\Desktop\\tradingDemo";
////        File file = new File(path);
////        List<File> files = FileSearchUtils.searchByFileDuff(file, "class");
////        for(File f : files) {
////            if(f.getName().equals("DealController.class")) {
////                ClassFile cls = new ClassFile(new DataInputStream(new BufferedInputStream(new FileInputStream(f))));
////                getMethodParamNames(cls, "getDealById");
////            }
////        }
//        String strs = new Test().getMethodParams("DealController", "getDealById");
//        System.out.println(strs);
////        for(String str : strs) {
////            System.out.println(str);
////        }
//    }
//
////    public String[] getMethodParamNames(String clazz, String method) throws NotFoundException {
////        ClassPool pool = ClassPool.getDefault();
////        ClassClassPath classPath = new ClassClassPath(this.getClass());
////        pool.insertClassPath(classPath);
////        CtClass cc = pool.get(clazz);
////        CtMethod cm = cc.getDeclaredMethod(method);
////        MethodInfo methodInfo = cm.getMethodInfo();
////        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
////        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
////        String[] paramNames = null;
////        if (attr != null) {
////            paramNames = new String[cm.getParameterTypes().length];
////            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
////            for (int i = 0; i < paramNames.length; i++)
////                paramNames[i] = attr.variableName(i + pos);
////        }
////        return paramNames;
////    }
//
//    private String getMethodParams(String className,String methodName){
//        String result="";
//        try{
//            ClassPool pool=ClassPool.getDefault();
//            ClassClassPath classPath = new ClassClassPath(TemplateVars.getClass());
//            pool.insertClassPath(classPath);
//            CtMethod cm =pool.getMethod(className, methodName);
//            // 使用javaassist的反射方法获取方法的参数名
//            MethodInfo methodInfo = cm.getMethodInfo();
//            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
//            result=cm.getName() + "(";
//            if (attr == null) {
//                return result + ")";
//            }
//            CtClass[] pTypes=cm.getParameterTypes();
//            String[] paramNames = new String[pTypes.length];
//            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//            for (int i = 0; i < paramNames.length; i++) {
//                if(!pTypes[i].getSimpleName().startsWith("HttpServletRe")){
//                    result += pTypes[i].getSimpleName();
//                    paramNames[i] = attr.variableName(i + pos);
//                    result += " " + paramNames[i]+",";
//                }
//            }
//            if(result.endsWith(",")){
//                result=result.substring(0, result.length()-1);
//            }
//            result+=")";
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//}
