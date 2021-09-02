package com.neeq.tradingDemo.controller;

import com.neeq.tradingDemo.model.Stock;
import com.neeq.tradingDemo.result.Result;
import com.neeq.tradingDemo.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.neeq.tradingDemo.result.CodeMsg.SERVER_ERROR;

/**
 * @author 宋立成
 * @date 2021/8/3 17:54
 */
@Api(tags = "股票管理")
@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation("列表查询")
    @RequestMapping(value = "getAllStocks", method = RequestMethod.GET)
    public Result<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        if(!stocks.isEmpty()) {
            return Result.success(stocks);
        }
        return Result.error(SERVER_ERROR);
    }

    @ApiOperation(value = "获取单个股票详细信息", notes = "传入需查看详情的ID", response = Stock.class)
    @RequestMapping(value = "getStockById", method = RequestMethod.POST)
    public Result<Stock> getStockById(Integer id) {
        Stock stock = stockService.getStockById(id);
        if(stock != null) {
            return Result.success(stock);
        }
        return Result.error(SERVER_ERROR);
    }

    @ApiOperation(value = "增加股票", notes = "传入Stock对象")
    @RequestMapping(value = "addStock", method = RequestMethod.POST)
    public void addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
    }

    @ApiOperation(value = "删除股票", notes = "传入需删除的股票ID")
    @RequestMapping(value = "deleteStock", method = RequestMethod.POST)
    public void deleteStock(Integer id) {
        stockService.deleteStock(id);
    }

    @ApiOperation(value = "修改股票", notes = "传入新的股票信息")
    @RequestMapping(value = "updateStock", method = RequestMethod.POST)
    public void updateStock(@RequestBody Stock stock) {
        stockService.updateStock(stock);
    }
}
