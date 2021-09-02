package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 股票信息
 * @author 宋立成
 * @date 2021/8/2 10:35
 */
@Repository
public interface StockMapper {
    /**
     * 查找所有股票
     * @return
     */
    List<Stock> getAllStocks();

    /**
     * 根据股票id查询
     * @param id
     * @return
     */
    Stock getStockById(Integer id);

    /**
     * 增加股票
     * @param stock
     */
    void addStock(Stock stock);

    /**
     * 根据股票id删除股票
     * @param id
     */
    void deleteStock(Integer id);

    /**
     * 修改股票
     * @param stock
     */
    void updateStock(Stock stock);
}
