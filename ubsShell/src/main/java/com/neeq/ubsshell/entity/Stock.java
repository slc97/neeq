package com.neeq.ubsshell.entity;

/**
 * @author 宋立成
 * @date 2021/8/2 11:54
 */

public class Stock {

    private Integer id;

    private String type;

    private String name;

    private String englishName;

    private Integer isinCode;

    public Stock(String s) {
        String[] strs = s.split(",");
        this.type = String.valueOf(strs[0]);
        this.name = String.valueOf(strs[1]);
        this.englishName = String.valueOf(strs[2]);
        this.isinCode = Integer.valueOf(strs[3]);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(Integer isinCode) {
        this.isinCode = isinCode;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", isinCode=" + isinCode +
                '}';
    }
}
