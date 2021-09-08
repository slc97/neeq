package com.neeq.ubsshell.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author 宋立成
 * @date 2021-09-02 17:50
 */
@Component
public class HttpUtil {

    @Autowired
    RestTemplateUtil restTemplateUtil;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getUrl(String url) {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip += inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String port = "8083";
        if(url.contains(ip)) {
            return url;
        }
        return "http://" + ip + ":" + port + url;
    }

    /**
     * 返回封装好的Request Body
     *
     * @param params
     * @return
     */
    public HttpEntity getEntity(Map params) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        System.out.println(JSON.toJSONString(params));
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(params), headers);
        return httpEntity;
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     */
    public String get(String url, Map params) {
        if(params.isEmpty()) {
            return restTemplate.getForEntity(getUrl(url),String.class).getBody();
        }
        return restTemplate.getForEntity(getUrl(url), String.class, params).getBody().toString();
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     */
    public String post(String url, Map params) {
        if(params.isEmpty()) {
            return restTemplate.postForEntity(getUrl(url), HttpEntity.EMPTY, String.class).getBody();
        }
        return (String) restTemplate.postForEntity(getUrl(url), getEntity(params), String.class, params).getBody();
    }

    /**
     * put请求
     *
     * @param url
     * @param params
     * @return
     */
    public String put(String url, Map params) {
        if(params.isEmpty()) {
            return restTemplateUtil.put(url);
        }
        return restTemplateUtil.put(url, params);
    }


    /**
     * delete请求
     *
     * @param url
     * @param params
     * @return
     */
    public String delete(String url, Map params) {
        if(params.isEmpty()) {
            return restTemplateUtil.delete(url);
        }
        return restTemplateUtil.delete(url, params);
    }

//    public String jsonExc (String url, HttpMethod method, String json) {
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<String> httpEntity = new HttpEntity<>(json, headers);
//        return restTemplate.exchange(getUrl(url), method, httpEntity, String.class).getBody();
//    }

}
