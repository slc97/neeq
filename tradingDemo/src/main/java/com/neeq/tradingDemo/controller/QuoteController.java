package com.neeq.tradingDemo.controller;

import com.neeq.tradingDemo.model.Quote;
import com.neeq.tradingDemo.result.Result;
import com.neeq.tradingDemo.service.QuoteService;
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
@Api(tags = "行情管理")
@RestController
@RequestMapping("quote")
public class QuoteController {
    @Autowired
    public QuoteService quoteService;

    @ApiOperation("列表查询")
    @RequestMapping(value = "/getAllQuotes", method = RequestMethod.GET)
    public Result<List<Quote>> getAllQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        if(!quotes.isEmpty()) {
            return Result.success(quotes);
        }
        return Result.error(SERVER_ERROR);
    }

    @ApiOperation(value = "获取单个行情详细信息", notes = "传入需查看详情的ID", response = Quote.class)
    @RequestMapping(value = "getQuoteById", method = RequestMethod.POST)
    public Result<Quote> getQuoteById(Integer id) {
        Quote quote = quoteService.getQuoteById(id);
        if(quote == null) {
            return Result.error(SERVER_ERROR);
        }
        return Result.success(quote);
    }

    @ApiOperation(value = "增加行情", notes = "传入Quote对象")
    @RequestMapping(value = "addQuote", method = RequestMethod.POST)
    public Result<Integer> addQuote(@RequestBody Quote quote) {
        return Result.success(quoteService.addQuote(quote));
    }


}
