package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行情的mapper类
 * @author 宋立成
 * @date 2021/8/2 11:53
 */
@Repository
public interface QuoteMapper {

    /**
     * 查找所有行情
     * @return
     */
    List<Quote> getAllQuotes();

    /**
     * 根据行情id查询
     * @param id
     * @return
     */
    Quote getQuoteById(Integer id);

    /**
     * 增加行情Quote
     * @param Quote
     */
    int addQuote(Quote Quote);
}
