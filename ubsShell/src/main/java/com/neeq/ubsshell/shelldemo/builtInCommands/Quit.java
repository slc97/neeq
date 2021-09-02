package com.neeq.ubsshell.shelldemo.builtInCommands;

import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author 宋立成
 * @date 2021/8/17 10:33
 */
@ShellComponent
public class Quit extends BuiltInCommand {
    public Quit() {
    }

    @ShellMethod(
            value = "Exit the shell.",
            key = {"quit", "exit"}
    )
    public void quit() {
        throw new ExitRequest();
    }

    public interface Command {
    }
}

