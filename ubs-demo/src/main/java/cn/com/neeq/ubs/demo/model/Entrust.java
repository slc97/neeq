package cn.com.neeq.ubs.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 宋立成
 * @date 2021/8/2 11:56
 */
@ApiModel(description = "委托类")
public class Entrust {

    @ApiModelProperty(value = "ID", example = "3")
    private Integer id;

    @ApiModelProperty(value = "股票ID", example = "3")
    private Integer stockId;

    @ApiModelProperty(value = "申报数量", example = "100.0")
    private Double number;

    @ApiModelProperty(value = "申报价格", example = "33.3")
    private Double price;

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
