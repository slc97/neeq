package util;

import entity.TemplateVars;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;

/**
 * @author 宋立成
 * @date 2021-08-30 09:55
 */
public class FileGenerator {

    public static void javaGene(TemplateVars templateVars, String targetPath) throws IOException {
        if(templateVars.getEntityShell() == null) {
            return ;
        }
        /* 1.初始化 Velocity */
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "file");
        // 模板放置在resources中
        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "src/main/resources");
        velocityEngine.init();

        /* 2.创建一个上下文对象 */
        VelocityContext context = new VelocityContext();

        /* 3.添加你的数据对象到上下文 */
        context.put("pkg" ,templateVars.getPkg());
        context.put("controllerFlag",templateVars.isControllerFlag());
        context.put("entity", templateVars.getEntity());
        context.put("clsUrl", templateVars.getClsUrl());
        context.put("entityShell", templateVars.getEntityShell());
        context.put("methods", templateVars.getMethods());
        context.put("CustomEntityConverter", templateVars.getCustomEntityConverter());
        context.put("fields", templateVars.getFields());

        /* 4.选择一个模板 */
        // TODO: 选择需要用的模板
        Template template = null;
        if(!templateVars.getEntity().equals("User")) {
            template = velocityEngine.getTemplate("template/shell.vm" );
        }
        else {
            template = velocityEngine.getTemplate("template/userShell.vm");
        }
//        System.out.println(templateVars.getEntityShell());
        /* 5.产生文件 */
        String targetFile = templateVars.getEntityShell();
        File file = new File(targetPath, targetFile+".java");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if(!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
        BufferedWriter sw = new BufferedWriter(writer);
        template.merge(context, sw);
        sw.flush();
        sw.close();
        outStream.close();
        System.out.println("成功生成Java文件:"
                + (targetPath + targetFile).replaceAll("/", "\\\\"));
    }
}
