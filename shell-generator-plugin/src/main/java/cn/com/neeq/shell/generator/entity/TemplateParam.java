package cn.com.neeq.shell.generator.entity;

/**
 * @author 宋立成
 * @date 2021/8/18 14:53
 */
public class TemplateParam {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TemplateParam{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
