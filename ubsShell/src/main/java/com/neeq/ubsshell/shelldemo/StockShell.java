package com.neeq.ubsshell.shelldemo;

import com.neeq.ubsshell.entity.Stock;
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
public class StockShell {
    @Autowired
    private RestTemplateUtil restTemplateUtil;


    @ShellMethod("列表查询")
    public String getAllStocks () {
        Map<String, Object> params = new HashMap<>();
        
        return restTemplateUtil.get("stockgetAllStocks", params);
    }

    @ShellMethod("获取单个股票详细信息")
    public String getStockById (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("stockgetStockById", params);
    }

    @ShellMethod("增加股票")
    public String addStock (Stock stock) {
        Map<String, Object> params = new HashMap<>();
        params.put("stock", stock);
        
        return restTemplateUtil.post("stockaddStock", params);
    }

    @ShellMethod("删除股票")
    public String deleteStock (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("stockdeleteStock", params);
    }

    @ShellMethod("修改股票")
    public String updateStock (Stock stock) {
        Map<String, Object> params = new HashMap<>();
        params.put("stock", stock);
        
        return restTemplateUtil.post("stockupdateStock", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("stock")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

class CustomStockConverter<T> implements Converter<String, Stock> {
    @Override
    public Stock convert(String s) {
        return new Stock(s);
    }
}