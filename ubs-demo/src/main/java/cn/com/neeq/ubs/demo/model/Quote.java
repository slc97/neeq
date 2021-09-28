package cn.com.neeq.ubs.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 宋立成
 * @date 2021/8/2 11:54
 */
@ApiModel(description = "行情类")
public class Quote {

    @ApiModelProperty(value = "ID", example = "3")
    private Integer id;

    @ApiModelProperty(value = "股票ID", example = "3")
    private Integer stockId;

    @ApiModelProperty(value = "股票名称", example = "股票C")
    private String stockName;

    @ApiModelProperty(value = "昨日收盘价", example = "300.0")
    private Double yestClosingPrice;

    @ApiModelProperty(value = "今日开盘价", example = "310.0")
    private Double todayOpeningPrice;

    @ApiModelProperty(value = "最近成交价", example = "400.0")
    private Double transactionPrice;

    @ApiModelProperty(value = "成交数量", example = "100.0")
    private Double transactionNumber;

    @ApiModelProperty(value = "成交金额", example = "40000.0")
    private Double transactionTotal;

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
