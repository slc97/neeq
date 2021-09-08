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
public class StockShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllStocks ( @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            
        }

        return httpUtil.get("/stock/getAllStocks", params);
    }

    @ShellMethod("获取单个股票详细信息")
    public String getStockById (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/stock/getStockById?id={id}", params);
    }

    @ShellMethod("增加股票")
    public String addStock (Integer id,String type,String name,String englishName,Integer isinCode, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            params.put("type" , type);
            params.put("name" , name);
            params.put("englishName" , englishName);
            params.put("isinCode" , isinCode);
            
        }

        return httpUtil.post("/stock/addStock?id={id}&type={type}&name={name}&englishName={englishName}&isinCode={isinCode}", params);
    }

    @ShellMethod("删除股票")
    public String deleteStock (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/stock/deleteStock?id={id}", params);
    }

    @ShellMethod("修改股票")
    public String updateStock (Integer id,String type,String name,String englishName,Integer isinCode, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            params.put("type" , type);
            params.put("name" , name);
            params.put("englishName" , englishName);
            params.put("isinCode" , isinCode);
            
        }

        return httpUtil.post("/stock/updateStock?id={id}&type={type}&name={name}&englishName={englishName}&isinCode={isinCode}", params);
    }


    @ShellMethodAvailability
    public Availability StockShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShell/stock")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

