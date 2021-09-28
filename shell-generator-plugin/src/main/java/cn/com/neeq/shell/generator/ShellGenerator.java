package cn.com.neeq.shell.generator;

import cn.com.neeq.shell.generator.entity.TemplateClazz;
import cn.com.neeq.shell.generator.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static cn.com.neeq.shell.generator.utils.FileGeneratorUtil.javaClear;

/**
 * @author 宋立成
 * @date 2021/8/20 16:08
 */
@Slf4j
@Mojo(name = "shell-generator")
public class ShellGenerator extends AbstractMojo {

    @Override
    public void execute() {
        String f = "src/main/resources/configure.properties";
        Properties props = new Properties();
        try {
            props.load(new java.io.FileInputStream(f));
        } catch (IOException e) {
            log.info(e+": configure loading failure.");
        }

        String path = props.getProperty("path");
        String targetPath = props.getProperty("targetPath");

        // 获取信息
        List<String> jars = null;
        if(path == null) {
            jars = FileSearchUtil.scanJar();
        } else {
            jars = FileSearchUtil.scanJar(path);
        }

        // 加载Jar包到classpath
        ExtClasspathLoaderUtil.loadClasspath(jars);

        // 获取信息
        List<TemplateClazz> templateClazzList = null;
        for(String jar : jars) {
            templateClazzList = InfoScanUtil.getInfo(jar);
        }

        File target = new File(targetPath);
        if(!target.exists()) {
            target.mkdir();
        } else {
            javaClear(target);
        }

        // 为每个接口创建shell类，并生成文件
        for(TemplateClazz templateClazz : templateClazzList) {
            FileGeneratorUtil.javaGene(templateClazz, targetPath);
        }
    }
}
