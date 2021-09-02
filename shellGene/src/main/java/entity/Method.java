package entity;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/18 14:50
 */
public class Method {

    private String methodDoc;

    private String methodName;

    private String url;

    private String requestMethod;

    private List<Param> params;

    public String getMethodDoc() {
        return methodDoc;
    }

    public void setMethodDoc(String methodDoc) {
        this.methodDoc = methodDoc;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }
}
