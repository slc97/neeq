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
public class EntrustShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("增加委托")
    public String addEntrust (java.lang.Integer id,Integer stockId,Double number,Double price, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            templateParams.put("stockId" , stockId);
            templateParams.put("number" , number);
            templateParams.put("price" , price);
            
        }
        return httpUtil.post("entrustaddEntrust?id={id}&stockId={stockId}&number={number}&price={price}", templateParams);
    }

    @ShellMethod("删除委托")
    public String deleteEntrust (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("entrustdeleteEntrust?id={id}", templateParams);
    }

    @ShellMethod("获取单个委托详细信息")
    public String getEntrustById (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("entrustgetEntrustById?id={id}", templateParams);
    }

    @ShellMethod("列表查询")
    public String getAllEntrusts ( @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            
        }
        return httpUtil.get("entrustgetAllEntrusts?", templateParams);
    }


    @ShellMethodAvailability
    public Availability EntrustShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShellentrust")
        ? Availability.available()
        : Availability.unavailable("no this command");
    }
}
