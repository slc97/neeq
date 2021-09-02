package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Deal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 15:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DealMapperTest {

    @Autowired
    private DealMapper dealMapper;

    @Test
    public void getAllDealsTest() {
        List<Deal> deals = dealMapper.getAllDeals();
        if(deals.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Deal deal : deals) {
            System.out.println(deal);
        }
    }

    @Test
    public void getDealByIdTest() {
        System.out.println(dealMapper.getDealById(1));
    }

    @Test
    public void addDealTest() {
        Deal deal = new Deal();
        deal.setStockId(1);
        deal.setEntrustId(1);
        deal.setNumber(20.0);
        deal.setPrice(15.0);

        dealMapper.addDeal(deal);
        this.getAllDealsTest();
    }

    @Test
    public void deleteDealTest() {
        dealMapper.deleteDeal(2);
        this.getAllDealsTest();
    }
}
