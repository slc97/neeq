package cn.com.neeq.ubs.demo.mapper;

import cn.com.neeq.ubs.demo.model.Entrust;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(EntrustMapperTest.class);

    @Autowired
    private EntrustMapper entrustMapper;

    @Test
    public void getAllEntrustsTest() {
        List<Entrust> entrusts = entrustMapper.getAllEntrusts();
        if(entrusts.isEmpty()) {
            logger.debug("此时无数据");
        }
        for(Entrust entrust : entrusts) {
            logger.debug(entrust.toString());
        }
    }

    @Test
    public void getEntrustByIdTest() {
        logger.debug(entrustMapper.getEntrustById(2).toString());
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
