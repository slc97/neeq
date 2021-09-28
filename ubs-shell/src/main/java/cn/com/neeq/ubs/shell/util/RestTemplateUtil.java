package cn.com.neeq.ubs.shell.util;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
}
