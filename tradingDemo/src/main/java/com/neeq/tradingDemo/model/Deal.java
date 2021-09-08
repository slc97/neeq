package com.neeq.tradingDemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 宋立成
 * @date 2021/8/2 11:56
 */
@ApiModel(description = "成交类")
public class Deal {

    @ApiModelProperty(value = "ID", example = "3")
    private int id;

    @ApiModelProperty(value = "股票ID", example = "2")
    private Integer stockId;

    @ApiModelProperty(value = "委托ID", example = "2")
    private Integer entrustId;

    @ApiModelProperty(value = "成交数量", example = "12.0")
    private Double number;

    @ApiModelProperty(value = "成交价格", example = "200.0")
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", entrustId=" + entrustId +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
