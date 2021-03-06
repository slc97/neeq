package cn.com.neeq.ubs.demo.service.impl;

import cn.com.neeq.ubs.demo.mapper.QuoteMapper;
import cn.com.neeq.ubs.demo.model.Quote;
import cn.com.neeq.ubs.demo.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:29
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteMapper quoteMapper;

    /**
     * 查找所有行情
     * @return
     */
    @Override
    public List<Quote> getAllQuotes() {
        return quoteMapper.getAllQuotes();
    }

    /**
     * 根据行情id查询
     * @param id
     * @return
     */
    @Override
    public Quote getQuoteById(Integer id) {
        return quoteMapper.getQuoteById(id);
    }

    /**
     * 增加行情Quote
     * @param quote
     */
    @Override
    public int addQuote(Quote quote) {
        return quoteMapper.addQuote(quote);
    }
}
