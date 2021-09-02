package com.neeq.tradingDemo.service;

import com.neeq.tradingDemo.model.Deal;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class DealServiceTest {

    @Autowired
    private DealService dealService;

    @Test
    public void getAllDealsTest() {
        List<Deal> deals = dealService.getAllDeals();
        if(deals.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Deal deal : deals) {
            System.out.println(deal);
        }
    }

    @Test
    public void getDealByIdTest() {
        System.out.println(dealService.getDealById(3));
    }

    @Test
    public void addDealTest() {
        Deal deal = new Deal();
        deal.setStockId(1);
        deal.setEntrustId(1);
        deal.setNumber(18.5);
        deal.setPrice(20.0);

        dealService.addDeal(deal);
        this.getAllDealsTest();
    }

    @Test
    public void deleteDeal() {
        dealService.deleteDeal(4);
        this.getAllDealsTest();
    }

}
