package com.neeq.ubsshell.entity;

/**
 * @author 宋立成
 * @date 2021/8/2 11:56
 */
public class Entrust {

    private Integer id;

    private Integer stockId;

    private Double number;

    private Double price;

    public Entrust(String s) {
        String[] strs = s.split(",");
        this.stockId = Integer.valueOf(strs[0]);
        this.number = Double.valueOf(strs[1]);
        this.price = Double.valueOf(strs[2]);
    }

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
        return "Entrust{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
