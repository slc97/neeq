package cn.com.neeq.ubs.shell.shelldemo;

import cn.com.neeq.ubs.shell.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class UserShell {
    @Autowired
    private HttpUtil httpUtil;

    private boolean connected = false;
    private String username;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ShellMethod("login")
    public String neeq(String name, String password) {
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("name", name);
        templateParams.put("password", password);

        String s = httpUtil.post("/login?name={name}&password={password}", templateParams);

        if(s.equals("Welcome to neeq")) {
            this.setConnected(true);
            this.setUsername(name);
        }
        return s;
    }

    @ShellMethodAvailability
    public Availability UserShellCheck() {
        return !this.isConnected() ? Availability.available() : Availability.unavailable("You have logged in.");
    }
}