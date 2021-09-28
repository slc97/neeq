package cn.com.neeq.shell.generator.utils;

import cn.com.neeq.shell.generator.entity.TemplateClazz;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;

/**
 * 文件生成工具类
 *
 * @author 宋立成
 * @date 2021-08-30 09:55
 */
@Slf4j
public class FileGeneratorUtil {

    /**
     * 清除目录下的文件
     * @param target
     */
    public static void javaClear(File target) {
        File[] files = target.listFiles();
        for(File file : files) {
            file.delete();
        }
    }

    /**
     * 创建java函数
     * @param templateClazz
     * @param targetPath
     * @throws IOException
     */
    public static void javaGene(TemplateClazz templateClazz, String targetPath) {
        if(templateClazz.getEntityShell() == null) {
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
        context.put("pkg" , templateClazz.getPkg());
        context.put("controllerFlag", templateClazz.isControllerFlag());
        context.put("entity", templateClazz.getEntity());
        context.put("clsUrl", templateClazz.getClsUrl());
        context.put("entityShell", templateClazz.getEntityShell());
        context.put("templateMethods", templateClazz.getMethods());
        context.put("CustomEntityConverter", templateClazz.getCustomEntityConverter());
        context.put("fields", templateClazz.getFields());

        /* 4.选择一个模板 */
        Template template = null;
        if(!templateClazz.getEntity().equals("User")) {
            template = velocityEngine.getTemplate("template/shell.vm" );
        }
        else {
            template = velocityEngine.getTemplate("template/userShell.vm");
        }

        /* 5.产生文件 */
        String targetFile = templateClazz.getEntityShell();
        File file = new File(targetPath, targetFile+".java");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream outStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
                BufferedWriter sw = new BufferedWriter(writer);
                template.merge(context, sw);
                sw.flush();
                sw.close();
                outStream.close();
                log.info("成功生成Java文件:"
                        + (targetPath + targetFile).replaceAll("/", "\\\\"));
            } catch (IOException e) {
                log.info(e+": creating file failure.");
            }
        }
    }
}
