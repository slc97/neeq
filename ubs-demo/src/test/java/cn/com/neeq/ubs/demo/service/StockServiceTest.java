package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Stock;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void getAllStocksTest() {
        List<Stock> stocks = stockService.getAllStocks();
        if(stocks.isEmpty()) {
            log.info("此时无数据");
        }
        for(Stock stock : stocks) {
            log.info(stock.toString());
        }
    }

    @Test
    public void getStockByIdTest() {
        log.info(stockService.getStockById(1).toString());
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
