package cn.com.neeq.shell.generator.entity;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/18 14:50
 */
public class TemplateMethod {

    private String methodDoc;

    private String methodName;

    private String url;

    private String requestMethod;

    private List<TemplateParam> templateParams;

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

    public List<TemplateParam> getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(List<TemplateParam> templateParams) {
        this.templateParams = templateParams;
    }

    @Override
    public String toString() {
        return "TemplateMethod{" +
                "methodDoc='" + methodDoc + '\'' +
                ", methodName='" + methodName + '\'' +
                ", url='" + url + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", templateParams=" + templateParams.toString() +
                '}';
    }
}
