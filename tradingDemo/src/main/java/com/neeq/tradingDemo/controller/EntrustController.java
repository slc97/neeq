package com.neeq.tradingDemo.controller;

import com.neeq.tradingDemo.model.Entrust;
import com.neeq.tradingDemo.result.Result;
import com.neeq.tradingDemo.service.EntrustService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.neeq.tradingDemo.result.CodeMsg.SERVER_ERROR;

/**
 * @author 宋立成
 * @date 2021/8/3 17:54
 */
@Api(tags = "委托管理")
@RestController
@RequestMapping("entrust")
public class EntrustController {
    @Autowired
    private EntrustService entrustService;

    @ApiOperation("列表查询")
    @RequestMapping(value = "getAllEntrusts", method = RequestMethod.GET)
    public Result<List<Entrust>> getAllEntrusts() {
        List<Entrust> entrusts = entrustService.getAllEntrusts();
        if(!entrusts.isEmpty()) {
            return Result.success(entrusts);
        }
        return Result.error(SERVER_ERROR);
    }

    @ApiOperation(value = "获取单个委托详细信息", notes = "传入需查看详情的ID", response = Entrust.class)
    @RequestMapping(value = "getEntrustById", method = RequestMethod.POST)
    public Result<Entrust> getEntrustById(Integer id) {
        Entrust entrust = entrustService.getEntrustById(id);
        if(entrust != null) {
            return Result.success(entrust);
        }
        return Result.error(SERVER_ERROR);
    }

    @ApiOperation(value = "增加委托", notes = "传入Entrust对象")
    @RequestMapping(value = "addEntrust", method = RequestMethod.POST)
    public void addEntrust(@RequestBody Entrust entrust) {
        entrustService.addEntrust(entrust);
    }

    @ApiOperation(value = "删除委托", notes = "传入需删除的行情ID")
    @RequestMapping(value = "deleteEntrust", method = RequestMethod.POST)
    public void deleteEntrust(Integer id) {
        entrustService.deleteEntrust(id);
    }
}
