package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Quote;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:00
 */
public interface QuoteService {
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
