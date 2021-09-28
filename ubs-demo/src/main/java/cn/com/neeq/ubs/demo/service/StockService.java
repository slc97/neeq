package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Stock;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:00
 */
public interface StockService {
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
