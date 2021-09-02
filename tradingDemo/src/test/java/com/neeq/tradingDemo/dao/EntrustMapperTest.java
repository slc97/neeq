package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Entrust;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 15:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrustMapperTest {
    @Autowired
    private EntrustMapper entrustMapper;

    @Test
    public void getAllEntrustsTest() {
        List<Entrust> entrusts = entrustMapper.getAllEntrusts();
        if(entrusts.isEmpty()) {
            System.out.println("此时无数据");
        }
        for(Entrust entrust : entrusts) {
            System.out.println(entrust);
        }
    }

    @Test
    public void getEntrustByIdTest() {
        System.out.println(entrustMapper.getEntrustById(2));
    }

    @Test
    public void addEntrustTest() {
        Entrust entrust = new Entrust();
        entrust.setStockId(1);
        entrust.setNumber(10.0);
        entrust.setPrice(12.0);

        entrustMapper.addEntrust(entrust);
        this.getAllEntrustsTest();
    }

    @Test
    public void deleteEntrustTest() {
        entrustMapper.deleteEntrust(2);
        this.getAllEntrustsTest();
    }
}
