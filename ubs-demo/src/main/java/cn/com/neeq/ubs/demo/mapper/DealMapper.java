package cn.com.neeq.ubs.demo.mapper;

import cn.com.neeq.ubs.demo.model.Deal;

import java.util.List;

/**
 * 成交的mapper类
 * @author 宋立成
 * @date 2021/8/2 11:53
 */
public interface DealMapper {
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
     * @param deal
     */
    void addDeal(Deal deal);

    /**
     * 根据成交id删除成交
     * @param id
     */
    void deleteDeal(Integer id);
}
