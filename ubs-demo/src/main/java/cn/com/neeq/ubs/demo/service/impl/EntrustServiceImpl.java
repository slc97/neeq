package cn.com.neeq.ubs.demo.service.impl;

import cn.com.neeq.ubs.demo.model.Entrust;
import cn.com.neeq.ubs.demo.service.EntrustService;
import cn.com.neeq.ubs.demo.mapper.EntrustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/3 16:29
 */
@Service
public class EntrustServiceImpl implements EntrustService {

    @Autowired
    private EntrustMapper entrustMapper;

    /**
     * 查找所有委托
     * @return
     */
    @Override
    public List<Entrust> getAllEntrusts() {
        return entrustMapper.getAllEntrusts();
    }

    /**
     * 根据委托id查询
     * @param id
     * @return
     */
    @Override
    public Entrust getEntrustById(Integer id) {
        return entrustMapper.getEntrustById(id);
    }

    /**
     * 增加委托
     * @param entrust
     */
    @Override
    public void addEntrust(Entrust entrust) {
        entrustMapper.addEntrust(entrust);
    }

    /**
     * 根据委托id删除委托
     * @param id
     */
    @Override
    public void deleteEntrust(Integer id) {
        entrustMapper.deleteEntrust(id);
    }
}
