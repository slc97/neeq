package util;

import java.util.Arrays;
import java.util.Locale;

/**
 * 信息扫描中的信息加工类
 *
 * @author 宋立成
 * @date 2021-09-01 09:02
 */
public class InfoProcessUtil {

    private static final String[] usualTypes = {"Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character","String"};

    /**
     * url处理
     *
     * @param protoUrl
     * @return
     */
    public static String urlProcess(String protoUrl) {
        String url = protoUrl.replace("{", "").replace("}", "").replace(" ", "").replace("\"", "");
        // 存在多个的url，默认使用第一个
        if(url.contains(",")) {
            String result = url.split(",")[0];
            return result.startsWith("/") ? result : "/"+result;
        }
        return url.startsWith("/") ? url : "/"+url;
    }


    /**
     * HTTP请求方式处理
     *
     * @param protoRequest
     * @return
     */
    public static String requestProcess(String protoRequest) {
        String[] url = protoRequest.split("\\.");
        return url[url.length-1].replace("}", "").toLowerCase();
    }


    /**
     * 参数类型处理
     *
     * @param protoType
     * @return
     */
    public static String[] paramTypeProcess(String protoType) {
        String[] types = protoType.split("\\)")[0].split(";");
        for(int i = 0; i < types.length; ++i) {
            if (types[i].startsWith("(L")) {
                types[i] = types[i].replace("(L", "");
            } else if (types[i].startsWith("L")) {
                types[i] = types[i].substring(1);
            }
            String[] strs = types[i].split("/");
            if(Arrays.asList(usualTypes).contains(strs[strs.length-1])) {
                types[i] = strs[strs.length-1];
            }
            else {
                types[i] = types[i].replace("/", ".");
            }
        }
        return types;
    }
}
