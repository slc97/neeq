package com.neeq.tradingDemo.service;

import com.neeq.tradingDemo.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void getAllStocksTest() {
        List<Stock> stocks = stockService.getAllStocks();
        if(stocks.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    @Test
    public void getStockByIdTest() {
        System.out.println(stockService.getStockById(1));
    }

    @Test
    public void addStockTest() {
        Stock stock = new Stock();
        stock.setType("类型X");
        stock.setName("股票C");
        stock.setEnglishName("Stock_C");
        stock.setIsinCode(18626);

        stockService.addStock(stock);
        this.getAllStocksTest();
    }

    @Test
    public void deleteStockTest() {
        stockService.deleteStock(3);
        this.getAllStocksTest();
    }
}
