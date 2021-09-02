package com.neeq.tradingDemo.service.impl;

import com.neeq.tradingDemo.dao.DealMapper;
import com.neeq.tradingDemo.model.Deal;
import com.neeq.tradingDemo.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:22
 */
@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealMapper dealMapper;
    /**
     * 查找所有
     * @return
     */
    @Override
    public List<Deal> getAllDeals() {
        return dealMapper.getAllDeals();
    }

    /**
     * 根据成交id查询
     * @param id
     * @return
     */
    @Override
    public Deal getDealById(Integer id) {
        return dealMapper.getDealById(id);
    }

    /**
     * 增加成交
     */
    @Override
    public void addDeal(Deal deal) {
        System.out.println("调用了service");
        System.out.println(deal);
        dealMapper.addDeal(deal);
    }

    /**
     * 根据成交id删除成交
     * @param id
     */
    @Override
    public void deleteDeal(Integer id) {
        dealMapper.deleteDeal(id);
    }
}
