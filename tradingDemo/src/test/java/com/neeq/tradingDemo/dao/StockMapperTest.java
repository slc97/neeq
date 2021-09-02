package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/2 15:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockMapperTest {

    @Autowired
    private StockMapper stockMapper;

    @Test
    public void getAllStocksTest() {
        List<Stock> stocks = stockMapper.getAllStocks();
        if(stocks.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    @Test
    public void getStockByIdTest() {
        System.out.println(stockMapper.getStockById(2));
    }

    @Test
    public void addStockTest() {
        Stock stock = new Stock();
        stock.setType("类型1");
        stock.setName("test2");
        stock.setEnglishName("test_one");
        stock.setIsinCode(10086);
        stockMapper.addStock(stock);
        this.getAllStocksTest();
    }

    @Test
    public void deleteStockTest() {
        stockMapper.deleteStock(2);
        this.getAllStocksTest();
    }

    @Test
    public void updateStockTest() {
        Stock stock = new Stock();
        stock.setId(2);
        stock.setType("类型2");
        stock.setName("test2");
        stock.setEnglishName("test_two");
        stock.setIsinCode(10010);
        stockMapper.updateStock(stock);
        this.getAllStocksTest();
    }
}
