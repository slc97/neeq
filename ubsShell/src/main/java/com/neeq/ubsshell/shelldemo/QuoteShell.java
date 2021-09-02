package com.neeq.ubsshell.shelldemo;

import com.neeq.ubsshell.entity.Quote;
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
public class QuoteShell {
    @Autowired
    private RestTemplateUtil restTemplateUtil;


    @ShellMethod("列表查询")
    public String getAllQuotes () {
        Map<String, Object> params = new HashMap<>();
        
        return restTemplateUtil.get("quote/getAllQuotes", params);
    }

    @ShellMethod("获取单个行情详细信息")
    public String getQuoteById (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("quotegetQuoteById", params);
    }

    @ShellMethod("增加行情")
    public String addQuote (Quote quote) {
        Map<String, Object> params = new HashMap<>();
        params.put("quote", quote);
        
        return restTemplateUtil.post("quoteaddQuote", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("quote")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

class CustomQuoteConverter<T> implements Converter<String, Quote> {
    @Override
    public Quote convert(String s) {
        return new Quote(s);
    }
}