package com.neeq.tradingDemo.controller;

import com.neeq.tradingDemo.model.Deal;
import com.neeq.tradingDemo.result.Result;
import com.neeq.tradingDemo.service.DealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.neeq.tradingDemo.result.CodeMsg.SERVER_ERROR;

/**
 * @author 宋立成
 * @date 2021/8/3 17:54
 */
@Api(tags = "成交管理")
@Controller
@ResponseBody
@RequestMapping({"/deal", "/anotherDeal"})
public class DealController {

    @Autowired
    private DealService dealService;

    @ApiOperation("列表查询")
    @RequestMapping(value = "/getAllDeals")
    public Result<List<Deal>> getAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        if(deals.isEmpty()) {
            return Result.error(SERVER_ERROR);
        }
        return Result.success(deals);
    }

    @ApiOperation(value = "获取单个成交详细信息", notes = "传入需查看详情的ID", response = Deal.class)
    @RequestMapping(value="/getDealById", method=RequestMethod.GET)
    public Deal getDealById(Integer id) {
        System.out.println("调用getdealbyid接口层"+id);
        return dealService.getDealById(id);
    }

    @ApiOperation(value = "增加成交", notes = "传入Deal对象")
    @RequestMapping(value="/addDeal", method=RequestMethod.POST)
    @ResponseBody
    public void addDeal(@RequestBody Deal deal) {
        System.out.println(deal.toString());
        dealService.addDeal(deal);
    }

    @ApiOperation(value = "删除成交", notes = "传入需删除的成交ID")
    @RequestMapping(value="/deleteDeal", method=RequestMethod.POST)
    @ResponseBody
    public void deleteDeal(Integer id) {
        dealService.deleteDeal(id);
    }
}
