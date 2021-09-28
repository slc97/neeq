package cn.com.neeq.ubs.demo.mapper;

import cn.com.neeq.ubs.demo.model.Deal;
import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DealMapperTest {

//    private static Logger logger = LoggerFactory.getLogger(DealMapperTest.class);

    @Autowired
    private DealMapper dealMapper;

    @Test
    public void getAllDealsTest() {
        List<Deal> deals = dealMapper.getAllDeals();
        if(deals.isEmpty()) {
            log.info("此时无数据");
        }
        for(Deal deal : deals) {
            log.info(deal.toString());
        }
        log.debug("输出DEBUG级别日志");
        log.info("输出INFO级别日志");
        log.warn("输出WARN级别日志");
        log.error("输出ERROR级别日志");
    }

    @Test
    public void getDealByIdTest() {
        log.info(dealMapper.getDealById(3).toString());
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
