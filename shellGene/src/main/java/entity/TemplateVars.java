package entity;

import java.util.List;
import java.util.Map;

/**
 * 生成Shell类的属性
 * @author 宋立成
 * @date 2021/8/17 14:10
 */
public class TemplateVars {

    // 包名
    private String pkg = "com.neeq.ubsshell.shelldemo";

    private boolean controllerFlag = false;
    private boolean entityFlag = false;

    // 实体类名，对应可以生成ClassName，ConvertorName
    private String entity;

    private String clsUrl;

    private List<Method> methods;

    private List<Param> fields;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public boolean isControllerFlag() {
        return controllerFlag;
    }

    public void setControllerFlag(boolean controllerFlag) {
        this.controllerFlag = controllerFlag;
    }

    public boolean isEntityFlag() {
        return entityFlag;
    }

    public void setEntityFlag(boolean entityFlag) {
        this.entityFlag = entityFlag;
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

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public List<Param> getFields() {
        return fields;
    }

    public void setFields(List<Param> fields) {
        this.fields = fields;
    }
}
