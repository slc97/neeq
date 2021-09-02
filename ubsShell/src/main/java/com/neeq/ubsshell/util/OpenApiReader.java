package com.neeq.ubsshell.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 宋立成
 * @date 2021/8/6 16:12
 */
public class OpenApiReader {
    // 项目启动接口
    private String ip = "http://127.0.0.1";
    private String port = "8083";

    // 获取项目OpenApi的json格式
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
            System.out.println(e);
            e.printStackTrace();
        }
        return result;
    }

    // 解析OpenApi获取信息
    public void getApiInfo() {
        String json = this.getApiJson();
    }
}
