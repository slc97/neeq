package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Entrust;

import java.util.List;

/**
 * 委托的mapper类
 * @author 宋立成
 * @date 2021/8/2 11:53
 */
public interface EntrustMapper {

    /**
     * 查找所有委托
     * @return
     */
    List<Entrust> getAllEntrusts();

    /**
     * 根据委托id查询
     * @param id
     * @return
     */
    Entrust getEntrustById(Integer id);

    /**
     * 增加委托
     * @param entrust
     */
    void addEntrust(Entrust entrust);

    /**
     * 根据委托id删除委托
     * @param id
     */
    void deleteEntrust(Integer id);
}
