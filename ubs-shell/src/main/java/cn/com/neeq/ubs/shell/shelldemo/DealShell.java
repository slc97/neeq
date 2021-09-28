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
public class DealShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllDeals ( @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            
        }
        return httpUtil.post("/deal/getAllDeals?", templateParams);
    }

    @ShellMethod("获取单个成交详细信息")
    public String getDealById (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.get("/deal/getDealById?id={id}", templateParams);
    }

    @ShellMethod("增加成交")
    public String addDeal (int id,Integer stockId,Integer entrustId,Double number,double price, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            templateParams.put("stockId" , stockId);
            templateParams.put("entrustId" , entrustId);
            templateParams.put("number" , number);
            templateParams.put("price" , price);
            
        }
        return httpUtil.post("/deal/addDeal?id={id}&stockId={stockId}&entrustId={entrustId}&number={number}&price={price}", templateParams);
    }

    @ShellMethod("删除成交")
    public String deleteDeal (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("/deal/deleteDeal?id={id}", templateParams);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShell/deal")
        ? Availability.available()
        : Availability.unavailable("no this command");
    }
}
