package cn.com.neeq.shell.generator.entity;

import java.util.List;

/**
 * 生成Shell类的属性
 * @author 宋立成
 * @date 2021/8/17 14:10
 */
public class TemplateClazz {

    // 包名
    private String pkg = "cn.com.neeq.ubs.shell.shelldemo";

    private boolean controllerFlag = false;
    private boolean entityFlag = false;

    // 实体类名，对应可以生成ClassName，ConvertorName
    private String entity;

    private String clsUrl;

    private List<TemplateMethod> templateMethods;

    private List<TemplateParam> fields;

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

    public List<TemplateMethod> getMethods() {
        return templateMethods;
    }

    public void setMethods(List<TemplateMethod> templateMethods) {
        this.templateMethods = templateMethods;
    }

    public List<TemplateParam> getFields() {
        return fields;
    }

    public void setFields(List<TemplateParam> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "TemplateClazz{" +
                "pkg='" + pkg + '\'' +
                ", controllerFlag=" + controllerFlag +
                ", entityFlag=" + entityFlag +
                ", entity='" + entity + '\'' +
                ", clsUrl='" + clsUrl + '\'' +
                ", templateMethods=" + templateMethods +
                ", fields=" + fields +
                '}';
    }
}
