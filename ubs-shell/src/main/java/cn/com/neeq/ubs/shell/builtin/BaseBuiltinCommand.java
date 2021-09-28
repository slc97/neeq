package cn.com.neeq.ubs.shell.builtin;


import cn.com.neeq.ubs.shell.shelldemo.UserShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethodAvailability;

/**
 * @author 宋立成
 * @date 2021/8/17 11:01
 */
@ShellComponent
public class BaseBuiltinCommand {

    @Autowired
    private UserShell userShell;

    @ShellMethodAvailability
    public Availability ConnectedCheck() {
        return userShell.isConnected() ? Availability.available() : Availability.unavailable("You must login first.");
    }
}
