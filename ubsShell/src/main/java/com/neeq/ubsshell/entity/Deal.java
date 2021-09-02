package com.neeq.ubsshell.entity;

/**
 * @author 宋立成
 * @date 2021/8/2 11:56
 */
public class Deal {

    private Integer id;

    private Integer stockId;

    private Integer entrustId;

    private Double number;

    private Double price;

    public Deal(String s) {
        String[] strs = s.split(",");
        this.stockId = Integer.valueOf(strs[0]);
        this.entrustId = Integer.valueOf(strs[1]);
        this.number = Double.valueOf(strs[2]);
        this.price = Double.valueOf(strs[3]);
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
