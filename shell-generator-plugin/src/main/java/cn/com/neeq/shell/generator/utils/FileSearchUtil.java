package cn.com.neeq.shell.generator.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 工具类，扫描Jar包下的文件
 *
 * @author 宋立成
 * @date 2021-08-30 09:55
 */
@Slf4j
public class FileSearchUtil {

    /**
     * 从jar包中扫描Class
     * @param path
     * @return 类名
     */
    public static List<JarEntry> scanCls(String path) {
        List<JarEntry> classes = new ArrayList<>();
        JarFile jar = null;
        try {
            jar = new JarFile(path);
        } catch (IOException e) {
            log.info(e+": error when creating jar from its path.");
        }
        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        while (entry.hasMoreElements()) {
            jarEntry = entry.nextElement();
            classes.add(jarEntry);
        }
        return classes;
    }

    /**
     * 从jarEntry获取className
     * @param jarEntries
     * @return
     */
    public static List<String> getClassNames(List<JarEntry> jarEntries) {
        List<String> classNames = new ArrayList<>();
        for(JarEntry jarEntry : jarEntries) {
            String className = jarEntry.getName();
            if (className.charAt(0) == '/') {
                className = className.substring(1);
            }

            if (className.endsWith(".class")) {
                classNames.add(className.substring(0, className.length() - 6).replace("/" , "."));
            }
        }
        return classNames;
    }

    /**
     * 扫描目录下的jar包
     * @param path
     * @return
     */
    public static List<String> scanJar(String path) {
        List<String> result = new ArrayList<>();
        File file = new File(path);
        if (file.isFile()) {
            result.add(file.getAbsolutePath());
        }
        File[] subFolders = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().endsWith(".jar")) {
                    return true;
                }
                return false;
            }
        });
        if (subFolders != null) {
            for (File f : subFolders) {
                if (f.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(f.getAbsolutePath());
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(scanJar(f.getAbsolutePath()));
                }
            }
        }
        return result;
    }

    /**
     * 无Jar包路径配置时，默认从项目路径下扫描
     * @return
     */
    public static List<String> scanJar() {
        return scanJar(System.getProperty("user.dir"));
    }
}
