package com.neeq.ubsshell.shelldemo;

import com.neeq.ubsshell.entity.User;
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
public class UserShell {
    @Autowired
    private RestTemplateUtil restTemplateUtil;


    @ShellMethod("列表查询")
    public String getAllUsers () {
        Map<String, Object> params = new HashMap<>();
        
        return restTemplateUtil.get("$clsUrlgetAllUsers", params);
    }

    @ShellMethod("获取单个用户详细信息")
    public String getUserById (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("$clsUrlgetUserById", params);
    }

    @ShellMethod("增加股票")
    public String addUser (User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        
        return restTemplateUtil.post("$clsUrladdUser", params);
    }

    @ShellMethod("删除股票")
    public String deleteUser (Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        return restTemplateUtil.post("$clsUrldeleteUser", params);
    }

    @ShellMethod("根据用户名查询用户")
    public String findByUserName (String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        
        return restTemplateUtil.post("$clsUrlfindByUserName", params);
    }

    @ShellMethod("登录")
    public String login (String name, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        
        return restTemplateUtil.post("$clsUrllogin", params);
    }


    @ShellMethodAvailability
    public Availability DealShellCheck() {
        return GroupRecordUtil.getGroup().equals("$clsUrl")
                ? Availability.available()
                : Availability.unavailable("no this command");
    }
}

class CustomUserConverter<T> implements Converter<String, User> {
    @Override
    public User convert(String s) {
        return new User(s);
    }
}