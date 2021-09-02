package com.neeq.ubsshell.entity;

/**
 * @author 宋立成
 * @date 2021/8/2 11:54
 */
public class Quote {

    private Integer id;

    private Integer stockId;

    private String stockName;

    private Double yestClosingPrice;

    private Double todayOpeningPrice;

    private Double transactionPrice;

    private Double transactionNumber;

    private Double transactionTotal;

    public Quote(String s) {
        String[] strs = s.split(",");
        this.stockId = Integer.valueOf(strs[0]);
        this.stockName = String.valueOf(strs[1]);
        this.yestClosingPrice = Double.valueOf(strs[2]);
        this.todayOpeningPrice = Double.valueOf(strs[3]);
        this.transactionPrice = Double.valueOf(strs[4]);
        this.transactionNumber = Double.valueOf(strs[5]);
        this.transactionTotal = Double.valueOf(strs[6]);
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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getYestClosingPrice() {
        return yestClosingPrice;
    }

    public void setYestClosingPrice(Double yestClosingPrice) {
        this.yestClosingPrice = yestClosingPrice;
    }

    public Double getTodayOpeningPrice() {
        return todayOpeningPrice;
    }

    public void setTodayOpeningPrice(Double todayOpeningPrice) {
        this.todayOpeningPrice = todayOpeningPrice;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Double getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Double transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Double getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(Double transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", stockName='" + stockName + '\'' +
                ", yestClosingPrice=" + yestClosingPrice +
                ", todayOpeningPrice=" + todayOpeningPrice +
                ", transactionPrice=" + transactionPrice +
                ", transactionNumber=" + transactionNumber +
                ", transactionTotal=" + transactionTotal +
                '}';
    }
}
