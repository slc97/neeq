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

    public static void javaGene(TemplateVars templateVars) throws IOException {
        if(templateVars.getEntityShell() == null) {
            return ;
        }
        /* 1.初始化 Velocity */
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "file");
        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "src/main/resources");
        velocityEngine.init();

        /* 2.创建一个上下文对象 */
        VelocityContext context = new VelocityContext();

        /* 3.添加你的数据对象到上下文 */
        context.put("pkg" ,templateVars.getPkg());
        context.put("entity", templateVars.getEntity());
        context.put("clsUrl", templateVars.getClsUrl());
        context.put("entityShell", templateVars.getEntityShell());
        context.put("methods", templateVars.getMethods());
        context.put("CustomEntityConverter", templateVars.getCustomEntityConverter());

        /* 4.选择一个模板 */
        Template template = velocityEngine.getTemplate("template/shell.vm");

        /* 5.产生文件 */
        String targetPath = "C:\\Users\\32858\\Desktop\\ubsShell\\src\\main\\java\\com\\neeq\\ubsshell\\shelldemo".replace(".", "\\");
        String targetFile = templateVars.getEntityShell();
//        System.out.println(targetPath);
//        System.out.println(targetFile);
        File file = new File(targetPath, targetFile+".java");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if(!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream,
                "UTF-8");
        BufferedWriter sw = new BufferedWriter(writer);
        template.merge(context, sw);
        sw.flush();
        sw.close();
        outStream.close();
        System.out.println("成功生成Java文件:"
                + (targetPath + targetFile).replaceAll("/", "\\\\"));
    }
}
