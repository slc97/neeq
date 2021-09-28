package cn.com.neeq.ubs.shell.builtin;

import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author 宋立成
 * @date 2021/8/17 10:33
 */
@ShellComponent
public class Quit extends BaseBuiltinCommand {
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

