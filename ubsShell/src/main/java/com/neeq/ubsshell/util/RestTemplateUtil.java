package com.neeq.ubsshell.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author 宋立成
 * @date 2021/8/9 10:55
 */
@Component
public class RestTemplateUtil {

    private final RestTemplate restTemplate = new RestTemplate();

    //一些自定义的请求头参数
    public final String supplierID="";
    public final String interfacekey= "";


    /**
     * 获取绝对URL地址
     * @param url 相对URL
     * @return String 绝对URL
     */
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

    public HttpEntity<?> getDefaultHttpEntity() {
        HttpHeaders header = this.getDefaultHeader();
        return new HttpEntity<>(header);
    }

    // ----------------------------------GET-------------------------------------------------------

    /**
     * GET请求调用方式
     *
     * @param url 请求URL
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url) {
        return restTemplate.getForEntity(getUrl(url), String.class).getBody();
    }

    /**
     * GET请求调用方式
     *
     * @param url 请求URL
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, Object... uriVariables) {
        return restTemplate.getForEntity(getUrl(url), String.class, uriVariables).getBody();
    }

    /**
     * GET请求调用方式
     *
     * @param url 请求URL
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, Map<String, ?> uriVariables) {
        return restTemplate.getForEntity(getUrl(url), String.class, uriVariables).getBody();
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, Map<String, String> headers, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return get(getUrl(url), httpHeaders, uriVariables);
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, HttpHeaders headers, Object... uriVariables) {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return exchange(getUrl(url), HttpMethod.GET, requestEntity, uriVariables);
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, Map<String, String> headers, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return get(getUrl(url), httpHeaders, uriVariables);
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String get(String url, HttpHeaders headers, Map<String, ?> uriVariables) {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return exchange(getUrl(url), HttpMethod.GET, requestEntity, uriVariables);
    }

    // ----------------------------------POST-------------------------------------------------------

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @return
     */
    public String post(String url) {
        return restTemplate.postForEntity(getUrl(url), HttpEntity.EMPTY, String.class).getBody();
    }

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, Object requestBody) {
        return restTemplate.postForEntity(getUrl(url), requestBody, String.class).getBody();
    }

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, Object requestBody, Object... uriVariables) {
        return restTemplate.postForEntity(getUrl(url), requestBody, String.class, uriVariables).getBody();
    }

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, Object requestBody, Map<String, ?> uriVariables) {
        return restTemplate.postForEntity(getUrl(url), requestBody, String.class, uriVariables).getBody();
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, Map<String, String> headers, Object requestBody, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return post(getUrl(url), httpHeaders, requestBody, uriVariables);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, HttpHeaders headers, Object requestBody, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return post(getUrl(url), requestEntity, uriVariables);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, Map<String, String> headers, Object requestBody, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return post(getUrl(url), httpHeaders, requestBody, uriVariables);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, HttpHeaders headers, Object requestBody, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return post(getUrl(url), requestEntity, uriVariables);
    }

    /**
     * 自定义请求头和请求体的POST请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, HttpEntity<?> requestEntity, Object... uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.POST, requestEntity, String.class, uriVariables).getBody();
    }

    /**
     * 自定义请求头和请求体的POST请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String post(String url, HttpEntity<?> requestEntity, Map<String, ?> uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.POST, requestEntity, String.class, uriVariables).getBody();
    }

    // ----------------------------------PUT-------------------------------------------------------

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, Class<T> responseType, Object... uriVariables) {
        return put(getUrl(url), HttpEntity.EMPTY, responseType, uriVariables);
    }

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return put(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return put(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的PUT请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return put(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的PUT请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return put(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的PUT请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return put(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的PUT请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return put(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 自定义请求头和请求体的PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    /**
     * 自定义请求头和请求体的PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> put(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.PUT, requestEntity, responseType, uriVariables);
    }

    // ----------------------------------DELETE-------------------------------------------------------

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Class<T> responseType, Object... uriVariables) {
        return delete(getUrl(url), HttpEntity.EMPTY, responseType, uriVariables);
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return delete(getUrl(url), HttpEntity.EMPTY, responseType, uriVariables);
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return delete(getUrl(url), httpHeaders, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return delete(getUrl(url), httpHeaders, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return delete(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return delete(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
        return delete(getUrl(url), requestEntity, responseType, uriVariables);
    }

    /**
     * 自定义请求头和请求体的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.DELETE, requestEntity, responseType, uriVariables);
    }

    /**
     * 自定义请求头和请求体的DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> delete(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(getUrl(url), HttpMethod.DELETE, requestEntity, responseType, uriVariables);
    }


    // --------------------------------------通用方法-------------------------------------------


    /**
     * 通用调用方式
     *
     * @param url 请求URL
     * @param method 请求方法类型
     * @param requestEntity 请求头和请求体封装对象
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Object... uriVariables) {
        return restTemplate.exchange(getUrl(url), method, requestEntity, String.class, uriVariables).getBody();
    }

    /**
     * 通用调用方式
     *
     * @param url 请求URL
     * @param method 请求方法类型
     * @param requestEntity 请求头和请求体封装对象
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Map<String, ?> uriVariables) {
        return restTemplate.exchange(getUrl(url), method, requestEntity, String.class, uriVariables).getBody();
    }


    /**
     * DLT专用执行方法
     * @param param 请求参数：可以添加一些常量请求值
     * @param url 访问的url
     * @param method 请求的方法
     * @return
     */
    public String execute(MultiValueMap<String,Object> param, String url, HttpMethod method){
        HttpHeaders headers = getDefaultHeader();
        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.exchange(this.getUrl(url), method, requestEntity, String.class);
        return response.getBody();
    }

    public String sendRequest(String url, MultiValueMap<String, Object> param, HttpMethod method) {

        return "";
    }

    /**
     * 请求传入对象参数
     * @param param
     * @param url
     * @param <T>
     * @return
     */
    public <T> String execute(T param, String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(param), httpHeaders);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(getUrl(url),
                HttpMethod.POST, httpEntity, JSONObject.class);
        System.out.println(responseEntity);
        return responseEntity.toString();
    }

    /**
     * 获取默认的头请求信息
     * @return
     */
    public HttpHeaders getDefaultHeader(){
        String timestamp = ""+System.currentTimeMillis();
        String signature = EncoderByMd5(supplierID + timestamp + interfacekey);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("signature", signature);
        headers.add("timestamp", timestamp);
        return headers;
    }


    /**
     * 获取默认的参数
     * @return
     */
    public MultiValueMap<String,Object> getDefaultParam(){
        MultiValueMap<String,Object> defParam = new LinkedMultiValueMap<>();
        defParam.add("invoker","xx");
        defParam.add("operatorName","xx");
        return defParam;
    }


    /**
     * 通过MD5加密
     * @param str
     * @return
     */
    public String EncoderByMd5(String str){
        if (str == null) {
            return null;
        }
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            return base64en.encode(md5.digest(str.getBytes("utf-8"))).toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }



    /**
     * get请求
     * @param url 请求的url
     * @param jsonData 请求的json
     * @return
     */
    public String restGet(String url,String jsonData){

        return request(url, jsonData,HttpMethod.GET);

    }

    /**
     * @param url 请求的url
     * @param jsonData json数据
     * @param httpMethod
     * @return
     */
    private String request(String url, String jsonData,HttpMethod httpMethod) {

        ResponseEntity<String> response=null;

        try {
            if (url == null) {
                throw new IllegalArgumentException();
            }

            HttpEntity<String> requestEntity = new HttpEntity<String>(jsonData);

            response = restTemplate.exchange(url, httpMethod, requestEntity, String.class);

        }catch (Exception ex){

            ex.printStackTrace();

            return "";
        }

        return response.getBody().toString();
    }
}
