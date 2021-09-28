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
public class QuoteShell {
    @Autowired
    private HttpUtil httpUtil;

    @ShellMethod("列表查询")
    public String getAllQuotes ( @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            
        }
        return httpUtil.get("quote/getAllQuotes?", templateParams);
    }

    @ShellMethod("获取单个行情详细信息")
    public String getQuoteById (Integer id, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            
        }
        return httpUtil.post("quotegetQuoteById?id={id}", templateParams);
    }

    @ShellMethod("增加行情")
    public String addQuote (java.lang.Integer id,Integer stockId,String stockName,Double yestClosingPrice,Double todayOpeningPrice,Double transactionPrice,Double transactionNumber,Double transactionTotal, @ShellOption(defaultValue="{}") String json) {

        Map<String, Object> templateParams = new HashMap<>();
        if(!json.equals("{}")) {
            templateParams = JSONObject.parseObject(json, Map.class);
        } else{
            templateParams.put("id" , id);
            templateParams.put("stockId" , stockId);
            templateParams.put("stockName" , stockName);
            templateParams.put("yestClosingPrice" , yestClosingPrice);
            templateParams.put("todayOpeningPrice" , todayOpeningPrice);
            templateParams.put("transactionPrice" , transactionPrice);
            templateParams.put("transactionNumber" , transactionNumber);
            templateParams.put("transactionTotal" , transactionTotal);
            
        }
        return httpUtil.post("quoteaddQuote?id={id}&stockId={stockId}&stockName={stockName}&yestClosingPrice={yestClosingPrice}&todayOpeningPrice={todayOpeningPrice}&transactionPrice={transactionPrice}&transactionNumber={transactionNumber}&transactionTotal={transactionTotal}", templateParams);
    }


    @ShellMethodAvailability
    public Availability QuoteShellCheck() {
        return GroupRecordUtil.getGroup().equals("/ubsShellquote")
        ? Availability.available()
        : Availability.unavailable("no this command");
    }
}
