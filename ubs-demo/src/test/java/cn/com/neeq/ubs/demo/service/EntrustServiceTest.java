package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Entrust;
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
public class EntrustServiceTest {

    @Autowired
    private EntrustService entrustService;

    @Test
    public void getAllEntrustsTest() {
        List<Entrust> entrusts = entrustService.getAllEntrusts();
        if(entrusts.isEmpty()) {
            log.info("此时无数据");
        }
        for(Entrust entrust : entrusts) {
            log.info(entrust.toString());
        }
    }

    @Test
    public void getEntrustById() {
        log.info(entrustService.getEntrustById(1).toString());
    }

    @Test
    public void addEntrust() {
        Entrust entrust = new Entrust();
        entrust.setStockId(4);
        entrust.setNumber(14.5);
        entrust.setPrice(15.4);

        entrustService.addEntrust(entrust);
        this.getAllEntrustsTest();
    }

    @Test
    public void deleteEntrust() {
        entrustService.deleteEntrust(3);
        this.getAllEntrustsTest();
    }
}
