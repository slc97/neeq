package cn.com.neeq.ubs.shell.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 宋立成
 * @date 2021/8/6 16:12
 */
@Slf4j
public class OpenApiReader {
    // 项目启动接口
    private String ip = "http://127.0.0.1";
    private String port = "8083";

    /**
     * 获取项目OpenApi的json格式
     * @return
     */
    public String getApiJson() {
        String url = this.ip + ":" + this.port + "/v2/api-docs";
        String s;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((s = in.readLine()) != null) {
                result += s;
            }
        } catch (Exception e) {
            log.info(e+": url error.");
        }
        return result;
    }
}
