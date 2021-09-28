package cn.com.neeq.shell.generator.utils;

import java.util.Arrays;

/**
 * 信息扫描中的信息加工类
 *
 * @author 宋立成
 * @date 2021-09-01 09:02
 */
public class InfoProcessUtil {

    /**
     * 反射获取的注解信息处理
     *
     * @param protoAnno
     * @return
     */
    public static String annoProcess(String protoAnno) {
        String[] values = protoAnno.substring(1, protoAnno.length()-1).split(",");
        if(values.length > 0) {
            return values[0];
        }
        return "";
    }

}
