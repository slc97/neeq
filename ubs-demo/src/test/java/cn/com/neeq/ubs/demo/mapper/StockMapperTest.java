package cn.com.neeq.ubs.demo.mapper;

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
 * @date 2021/8/2 15:36
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockMapperTest {

    @Autowired
    private StockMapper stockMapper;

    @Test
    public void getAllStocksTest() {
        List<Stock> stocks = stockMapper.getAllStocks();
        if(stocks.isEmpty()) {
            log.info("此时无数据");
        }
        for(Stock stock : stocks) {
            log.info(stock.toString());
        }
    }

    @Test
    public void getStockByIdTest() {
        log.info(stockMapper.getStockById(2).toString());
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
