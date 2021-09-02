package entity;

import java.util.List;

/**
 * 生成Shell类的属性
 * @author 宋立成
 * @date 2021/8/17 14:10
 */
public class TemplateVars {

    // 包名
    private String pkg = "com.neeq.ubsshell.shelldemo";

    // 实体类名，对应可以生成ClassName，ConvertorName
    private String entity;

    private String clsUrl;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setEntityController(String controller) {
        String[] strs = controller.split("\\.");
        if(strs.length > 1) {
            setEntity(strs[strs.length - 1].replace("Controller", ""));
        }
    }

    public String getEntityShell() {
        if(entity == null) {
            return null;
        }
        return entity+"Shell";
    }

    public String getCustomEntityConverter() {
        return "Custom" + entity + "Converter";
    }

    public String getClsUrl() {
        return clsUrl;
    }

    public void setClsUrl(String clsUrl) {
        this.clsUrl = clsUrl;
    }

    private List<Method> methods;

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}
