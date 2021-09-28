package cn.com.neeq.ubs.demo.mapper;

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
 * @date 2021/8/2 17:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteMapperTest {
    @Autowired
    private QuoteMapper quoteMapper;

    @Test
    public void getAllQuotesTest() {
        List<Quote> quotes = quoteMapper.getAllQuotes();
        if(quotes.isEmpty()) {
            log.info("此时无数据");
        }
        for(Quote quote : quotes) {
            log.info(quote.toString());
        }
    }

    @Test
    public void getQuoteByIdTest() {
        log.info(quoteMapper.getQuoteById(3).toString());
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
        log.info(quote.getId().toString());
//        this.getAllQuotesTest();
    }
}
