package com.neeq.ubsshell.shelldemo;

import com.alibaba.fastjson.JSONObject;
import com.neeq.ubsshell.util.GroupRecordUtil;
import com.neeq.ubsshell.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class DealShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllDeals ( @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            
        }

        return httpUtil.post("/deal/getAllDeals", params);
    }

    @ShellMethod("获取单个成交详细信息")
    public String getDealById (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.get("/deal/getDealById?id={id}", params);
    }

    @ShellMethod("增加成交")
    public String addDeal (Integer id,Integer stockId,Integer entrustId,Double number,Double price, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            params.put("stockId" , stockId);
            params.put("entrustId" , entrustId);
            params.put("number" , number);
            params.put("price" , price);
            
        }

        return httpUtil.post("/deal/addDeal?id={id}&stockId={stockId}&entrustId={entrustId}&number={number}&price={price}", params);
    }

    @ShellMethod("删除成交")
    public String deleteDeal (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/deal/deleteDeal?id={id}", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShell/deal")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

