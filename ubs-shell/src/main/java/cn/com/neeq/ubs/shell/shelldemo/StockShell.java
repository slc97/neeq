package cn.com.neeq.ubs.shell.shelldemo;

import com.alibaba.fastjson.JSONObject;
import cn.com.neeq.ubs.shell.util.HttpUtil;
import cn.com.neeq.ubs.shell.util.GroupRecordUtil;
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

    @ShellMethod("增加股票")
    public String addStock (java.lang.Integer id,String type,String name,String englishName,Integer isinCode, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            templateParams.put("type" , type);
            templateParams.put("name" , name);
            templateParams.put("englishName" , englishName);
            templateParams.put("isinCode" , isinCode);
            
        }
        return httpUtil.post("stockaddStock?id={id}&type={type}&name={name}&englishName={englishName}&isinCode={isinCode}", templateParams);
    }

    @ShellMethod("获取单个股票详细信息")
    public String getStockById (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("stockgetStockById?id={id}", templateParams);
    }

    @ShellMethod("修改股票")
    public String updateStock (java.lang.Integer id,String type,String name,String englishName,Integer isinCode, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            templateParams.put("type" , type);
            templateParams.put("name" , name);
            templateParams.put("englishName" , englishName);
            templateParams.put("isinCode" , isinCode);
            
        }
        return httpUtil.post("stockupdateStock?id={id}&type={type}&name={name}&englishName={englishName}&isinCode={isinCode}", templateParams);
    }

    @ShellMethod("列表查询")
    public String getAllStocks ( @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            
        }
        return httpUtil.get("stockgetAllStocks?", templateParams);
    }

    @ShellMethod("删除股票")
    public String deleteStock (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("stockdeleteStock?id={id}", templateParams);
    }


    @ShellMethodAvailability
    public Availability StockShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShellstock")
        ? Availability.available()
        : Availability.unavailable("no this command");
    }
}
