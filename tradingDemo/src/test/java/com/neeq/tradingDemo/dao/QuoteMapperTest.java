package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/2 17:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteMapperTest {
    @Autowired
    private QuoteMapper quoteMapper;

    @Test
    public void getAllQuotesTest() {
        List<Quote> quotes = quoteMapper.getAllQuotes();
        if(quotes.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Quote quote : quotes) {
            System.out.println(quote);
        }
    }

    @Test
    public void getQuoteByIdTest() {
        System.out.println(quoteMapper.getQuoteById(3));
    }

    @Test
    public void addQuoteTest() {
        Quote quote = new Quote();
        quote.setStockId(1);
        quote.setStockName("Test3");
        quote.setYestClosingPrice(12.1);
        quote.setTodayOpeningPrice(12.3);
        quote.setTransactionNumber(10.0);
        quote.setTransactionPrice(12.2);
        quote.setTransactionTotal(122.0);

        quoteMapper.addQuote(quote);
        System.out.println(quote.getId());
//        this.getAllQuotesTest();
    }
}
