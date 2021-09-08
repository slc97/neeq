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
public class EntrustShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllEntrusts ( @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            
        }

        return httpUtil.get("/entrust/getAllEntrusts", params);
    }

    @ShellMethod("获取单个委托详细信息")
    public String getEntrustById (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/entrust/getEntrustById?id={id}", params);
    }

    @ShellMethod("增加委托")
    public String addEntrust (Integer id,Integer stockId,Double number,Double price, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            params.put("stockId" , stockId);
            params.put("number" , number);
            params.put("price" , price);
            
        }

        return httpUtil.post("/entrust/addEntrust?id={id}&stockId={stockId}&number={number}&price={price}", params);
    }

    @ShellMethod("删除委托")
    public String deleteEntrust (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/entrust/deleteEntrust?id={id}", params);
    }


    @ShellMethodAvailability
    public Availability EntrustShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShell/entrust")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

