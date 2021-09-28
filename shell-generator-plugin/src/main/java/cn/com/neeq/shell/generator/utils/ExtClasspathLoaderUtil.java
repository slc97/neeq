package cn.com.neeq.shell.generator.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * 工具类，将Jar包加载到Classpath
 *
 * @author 宋立成
 * @date 2021-08-30 09:55
 */
@Slf4j
public final class ExtClasspathLoaderUtil {

    /** URLClassLoader的addURL方法 */
    private static Method addURL = initAddMethod();

    private static URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();

    /**
     * 初始化addUrl 方法.
     * @return 可访问addUrl方法的Method对象
     */
    private static Method initAddMethod() {
        Method add = null;
        try {
            add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
            add.setAccessible(true);
        }
        catch (Exception e) {
            log.info(new RuntimeException(e) + ": error when initing classpath addMethod");
        }
        return add;
    }

    /**
     * 加载jar到classpath
     */
    public static void loadClasspath(List<String> jars) {
        for (String jar : jars) {
            addURL(new File(jar));
        }
    }

    /**
     * 通过filepath加载文件到classpath
     * @param file 文件路径
     * @return URL
     * @throws Exception 异常
     */
    private static void addURL(File file) {
        try {
            addURL.invoke(classloader, new Object[] { file.toURI().toURL() });
        }
        catch (Exception e) {
        }
    }
}
