package cn.com.neeq.ubs.shell.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 宋立成
 * @date 2021-09-01 10:10
 */
public class ParamPackingUtil {

    /**
     * 参数打包为
     *
     * @param args
     * @return
     */
    public static Map<String, Object> paramPacking(Object... args) {
        // 无参数
        if(args == null) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>();
        return paramMap;
    }
}
