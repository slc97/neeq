package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.Entrust;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:00
 */
public interface EntrustService {

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
