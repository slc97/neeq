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
public class QuoteShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllQuotes ( @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            
        }

        return httpUtil.get("/quote/getAllQuotes", params);
    }

    @ShellMethod("获取单个行情详细信息")
    public String getQuoteById (Integer id, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            
        }

        return httpUtil.post("/quote/getQuoteById?id={id}", params);
    }

    @ShellMethod("增加行情")
    public String addQuote (Integer id,Integer stockId,String stockName,Double yestClosingPrice,Double todayOpeningPrice,Double transactionPrice,Double transactionNumber,Double transactionTotal, @ShellOption(defaultValue="{}") String json) {
        Map<String, Object> params = new HashMap<>();
        if(!json.equals("{}")) {
            params = JSONObject.parseObject(json, Map.class);
        } else{
            params.put("id" , id);
            params.put("stockId" , stockId);
            params.put("stockName" , stockName);
            params.put("yestClosingPrice" , yestClosingPrice);
            params.put("todayOpeningPrice" , todayOpeningPrice);
            params.put("transactionPrice" , transactionPrice);
            params.put("transactionNumber" , transactionNumber);
            params.put("transactionTotal" , transactionTotal);
            
        }

        return httpUtil.post("/quote/addQuote?id={id}&stockId={stockId}&stockName={stockName}&yestClosingPrice={yestClosingPrice}&todayOpeningPrice={todayOpeningPrice}&transactionPrice={transactionPrice}&transactionNumber={transactionNumber}&transactionTotal={transactionTotal}", params);
    }


    @ShellMethodAvailability
    public Availability QuoteShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShell/quote")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

