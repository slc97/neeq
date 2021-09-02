package com.neeq.ubsshell.shelldemo.builtInCommands;

import com.neeq.ubsshell.shelldemo.UserShell;
import com.neeq.ubsshell.util.GroupRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethodAvailability;

/**
 * @author 宋立成
 * @date 2021/8/17 11:01
 */
@ShellComponent
public class BuiltInCommand {

    @Autowired
    private UserShell userShell;

    @ShellMethodAvailability
    public Availability ConnectedCheck() {
        return userShell.isConnected() ? Availability.available() : Availability.unavailable("You must login first.");
    }
}
