package com.neeq.ubsshell.shelldemo;

import com.neeq.ubsshell.entity.Deal;
import com.neeq.ubsshell.util.GroupRecordUtil;
import com.neeq.ubsshell.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class DealShell {
    @Autowired
    private RestTemplateUtil restTemplateUtil;


    @ShellMethod("列表查询")
    public String getAllDeals () {
        Map<String, Object> params = new HashMap<>();
        
        return restTemplateUtil.post("/deal/getAllDeals", params);
    }

    @ShellMethod("获取单个成交详细信息")
    public String getDealById (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("/deal/getDealById", params);
    }

    @ShellMethod("增加成交")
    public String addDeal (Deal deal, Integer z) {
        Map<String, Object> params = new HashMap<>();
        params.put("deal", deal);
        params.put("z", z);
        
        return restTemplateUtil.post("/deal/addDeal", params);
    }

    @ShellMethod("删除成交")
    public String deleteDeal (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("/deal/deleteDeal", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("/deal")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

class CustomDealConverter<T> implements Converter<String, Deal> {
    @Override
    public Deal convert(String s) {
        return new Deal(s);
    }
}