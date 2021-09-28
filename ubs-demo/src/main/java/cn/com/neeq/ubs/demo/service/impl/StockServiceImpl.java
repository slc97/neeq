package cn.com.neeq.ubs.demo.service.impl;

import cn.com.neeq.ubs.demo.mapper.StockMapper;
import cn.com.neeq.ubs.demo.model.Stock;
import cn.com.neeq.ubs.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:29
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    /**
     * 查找所有股票
     * @return
     */
    @Override
    public List<Stock> getAllStocks() {
        return stockMapper.getAllStocks();
    }

    /**
     * 根据股票id查询
     * @param id
     * @return
     */
    @Override
    public Stock getStockById(Integer id) {
        return stockMapper.getStockById(id);
    }

    /**
     * 增加股票
     * @param stock
     */
    @Override
    public void addStock(Stock stock) {
        stockMapper.addStock(stock);
    }

    /**
     * 根据股票id删除股票
     * @param id
     */
    @Override
    public void deleteStock(Integer id) {
        stockMapper.deleteStock(id);
    }

    /**
     * 修改股票
     * @param stock
     */
    @Override
    public void updateStock(Stock stock) {
        stockMapper.updateStock(stock);
    }
}
