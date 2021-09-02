package com.neeq.tradingDemo.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author 宋立成
 * @date 2021/8/4 17:33
 */
@Component
public class HttpRequest {

    /**
     * 获取IP地址跟端口号
     * @return
     */
    private String getUrl() {
        return "http://127.0.0.1:8083";
    }

    /**
     * 无参Get请求
     * @param url
     * @return
     */
    public String sendGet(String url) {
        return this.doGet(this.getUrl() + url);
    }

    /**
     * 含参Get请求
     * @param url
     * @param paramName
     * @param paramValue
     * @return
     */
    public String sendGet(String url, List<String> paramName, List<String> paramValue) {
        if(paramName.size() != paramValue.size()) {
            new Exception().printStackTrace();
        }
        int len = paramName.size();
        url = url + "?" + paramName.get(0) + "=" + paramValue.get(0);
        for(int i = 1; i < len; ++i) {
            url = url + "&" + paramName.get(i) + "=" + paramValue.get(i);
        }
        return this.doGet(this.getUrl() + url);
    }

    /**
     * 无参Post请求
     * @param url
     * @return
     */
    public String sendPost(String url) {
        return doPost(this.getUrl() + url, "");
    }

    /**
     * 含参Post请求
     * @param url
     * @param paramName
     * @param paramValue
     * @return
     */
    public String sendPost(String url, List<String> paramName, List<String> paramValue) {
        if(paramName.size() != paramValue.size()) {
            new Exception().printStackTrace();
        }
        int len = paramName.size();
        String param = paramName.get(0) + "=" + paramValue.get(0);
        for(int i = 1; i < len; ++i) {
            param = param + "&" + paramName.get(i) + "=" + paramValue.get(i);
        }
        return this.doPost(this.getUrl() + url, param);
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }

        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, String param) {
        DataOutputStream out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        int responseCode = 0;
        System.out.println("参数为" + param);
        try {
            URL realUrl = new URL(url);

            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");



            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
//            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(conn.getOutputStream());

            // 发送请求参数
            out.writeBytes(param);

            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
//            System.out.println(responseCode);
            e.printStackTrace();
        }

        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
