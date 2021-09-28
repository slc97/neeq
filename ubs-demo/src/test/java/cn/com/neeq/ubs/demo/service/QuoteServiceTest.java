package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Quote;
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
public class QuoteServiceTest {
    @Autowired
    private QuoteService quoteService;

    @Test
    public void getAllQuotesTest(){
        List<Quote> quotes = quoteService.getAllQuotes();
        if(quotes.isEmpty()) {
            log.info("此时无数据");
        }
        for(Quote quote : quotes) {
            log.info(quote.toString());
        }
    }

    @Test
    public void getQuoteByIdTest() {
        log.info(quoteService.getQuoteById(3).toString());
    }

    @Test
    public void addQuoteTest() {
        Quote quote = new Quote();
        quote.setStockId(4);
        quote.setStockName("股票X");
        quote.setYestClosingPrice(12.1);
        quote.setTodayOpeningPrice(13.0);
        quote.setTransactionPrice(22.0);
        quote.setTransactionNumber(100.0);
        quote.setTransactionTotal(2200.0);

        quoteService.addQuote(quote);
        this.getAllQuotesTest();
    }
}
