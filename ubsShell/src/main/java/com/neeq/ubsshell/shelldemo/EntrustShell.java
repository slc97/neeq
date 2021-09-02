package com.neeq.ubsshell.shelldemo;

import com.neeq.ubsshell.entity.Entrust;
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
public class EntrustShell {
    @Autowired
    private RestTemplateUtil restTemplateUtil;


    @ShellMethod("列表查询")
    public String getAllEntrusts () {
        Map<String, Object> params = new HashMap<>();
        
        return restTemplateUtil.get("entrustgetAllEntrusts", params);
    }

    @ShellMethod("获取单个委托详细信息")
    public String getEntrustById (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("entrustgetEntrustById", params);
    }

    @ShellMethod("增加委托")
    public String addEntrust (Entrust entrust) {
        Map<String, Object> params = new HashMap<>();
        params.put("entrust", entrust);
        
        return restTemplateUtil.post("entrustaddEntrust", params);
    }

    @ShellMethod("删除委托")
    public String deleteEntrust (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("entrustdeleteEntrust", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("entrust")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

class CustomEntrustConverter<T> implements Converter<String, Entrust> {
    @Override
    public Entrust convert(String s) {
        return new Entrust(s);
    }
}