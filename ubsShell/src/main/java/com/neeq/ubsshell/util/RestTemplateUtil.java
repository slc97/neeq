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

//    /**
//     * GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String get(String url, Object... uriVariables) {
//        return restTemplate.getForEntity(getUrl(url), String.class, uriVariables).getBody();
//    }

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

//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String get(String url, Map<String, String> headers, Object... uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return get(getUrl(url), httpHeaders, uriVariables);
//    }
//
//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String get(String url, HttpHeaders headers, Object... uriVariables) {
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//        return exchange(getUrl(url), HttpMethod.GET, requestEntity, uriVariables);
//    }
//
//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String get(String url, Map<String, String> headers, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return get(getUrl(url), httpHeaders, uriVariables);
//    }
//
//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String get(String url, HttpHeaders headers, Map<String, ?> uriVariables) {
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//        return exchange(getUrl(url), HttpMethod.GET, requestEntity, uriVariables);
//    }

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
     * @param url
     * @param uriVariables
     * @return
     */
    public String post(String url, Map uriVariables) {
        return restTemplate.postForEntity(getUrl(url), HttpEntity.EMPTY, String.class).getBody();
    }

//    /**
//     * POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param requestBody 请求参数体
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, Object requestBody) {
//        return restTemplate.postForEntity(getUrl(url), requestBody, String.class).getBody();
//    }
//
//    /**
//     * POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, Object requestBody, Object... uriVariables) {
//        return restTemplate.postForEntity(getUrl(url), requestBody, String.class, uriVariables).getBody();
//    }
//
//    /**
//     * POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, Object requestBody, Map<String, ?> uriVariables) {
//        return restTemplate.postForEntity(getUrl(url), requestBody, String.class, uriVariables).getBody();
//    }
//
//    /**
//     * 带请求头的POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, Map<String, String> headers, Object requestBody, Object... uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return post(getUrl(url), httpHeaders, requestBody, uriVariables);
//    }
//
//    /**
//     * 带请求头的POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, HttpHeaders headers, Object requestBody, Object... uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return post(getUrl(url), requestEntity, uriVariables);
//    }
//
//    /**
//     * 带请求头的POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, Map<String, String> headers, Object requestBody, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return post(getUrl(url), httpHeaders, requestBody, uriVariables);
//    }
//
//    /**
//     * 带请求头的POST请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public String post(String url, HttpHeaders headers, Object requestBody, Map<String, ?> uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return post(getUrl(url), requestEntity, uriVariables);
//    }

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
        ResponseEntity response = restTemplate.exchange(getUrl(url), HttpMethod.POST, requestEntity, String.class, uriVariables);
        System.out.println(response);
//        return restTemplate.exchange(getUrl(url), HttpMethod.POST, requestEntity, String.class, uriVariables).getBody();
        return (String) response.getBody();
    }

    // ----------------------------------PUT-------------------------------------------------------

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String put(String url, Object... uriVariables) {
        return put(getUrl(url), HttpEntity.EMPTY, String.class, uriVariables).getBody();
    }

    public String put(String url, Map<String, ?> uriVariables) {
        return put(getUrl(url), HttpEntity.EMPTY, String.class, uriVariables).getBody();
    }

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String put(String url, Object requestBody, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return put(getUrl(url), requestEntity, String.class, uriVariables).getBody();
    }

    /**
     * PUT请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String put(String url, Object requestBody, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return put(getUrl(url), requestEntity, String.class, uriVariables).getBody();
    }

//    /**
//     * 带请求头的PUT请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return put(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的PUT请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return put(getUrl(url), requestEntity, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的PUT请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return put(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的PUT请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return put(getUrl(url), requestEntity, responseType, uriVariables);
//    }

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
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String delete(String url, Object... uriVariables) {
        return delete(getUrl(url), HttpEntity.EMPTY, String.class, uriVariables).getBody();
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String delete(String url, Map<String, ?> uriVariables) {
        return delete(getUrl(url), HttpEntity.EMPTY, String.class, uriVariables).getBody();
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public String delete(String url, Object requestBody, Object... uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return delete(getUrl(url), requestEntity, String.class, uriVariables).getBody();
    }

    /**
     * DELETE请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public String delete(String url, Object requestBody, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return delete(getUrl(url), requestEntity, String.class, uriVariables).getBody();
    }

//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Object... uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return delete(getUrl(url), httpHeaders, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
//        return delete(getUrl(url), requestEntity, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return delete(getUrl(url), httpHeaders, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
//        return delete(getUrl(url), requestEntity, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return delete(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Object... uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return delete(getUrl(url), requestEntity, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return delete(getUrl(url), httpHeaders, requestBody, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的DELETE请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param requestBody 请求参数体
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, headers);
//        return delete(getUrl(url), requestEntity, responseType, uriVariables);
//    }

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
     * 请求传入对象参数
     * @param param
     * @param url
     * @param <T>
     * @return
     */
    public <T> String execute(T param, String url) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(param), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(getUrl(url), httpEntity, String.class);
        return responseEntity.toString();
    }
}
