package com.neeq.tradingDemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 宋立成
 * @date 2021/8/2 11:54
 */
@ApiModel(description = "股票类")
public class Stock {

    @ApiModelProperty(value = "ID", example = "3")
    private Integer id;

    @ApiModelProperty(value = "股票类型", example = "A股")
    private String type;

    @ApiModelProperty(value = "股票名称", example = "股票C")
    private String name;

    @ApiModelProperty(value = "英文名称", example = "Stock_C")
    private String englishName;

    @ApiModelProperty(value = "ISIN编码", example = "10010")
    private Integer isinCode;

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
