package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Deal;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:00
 */
public interface DealService {
    /**
     * 查找所有
     * @return
     */
    List<Deal> getAllDeals();

    /**
     * 根据成交id查询
     * @param id
     * @return
     */
    Deal getDealById(Integer id);

    /**
     * 增加成交
     */
    void addDeal(Deal deal);

    /**
     * 根据成交id删除成交
     * @param id
     */
    void deleteDeal(Integer id);
}
