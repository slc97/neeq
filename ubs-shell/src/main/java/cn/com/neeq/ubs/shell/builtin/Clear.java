package cn.com.neeq.ubs.shell.builtin;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * 重写Clear
 *
 * @author 宋立成
 * @date 2021/8/17 10:26
 */
@ShellComponent
public class Clear extends BaseBuiltinCommand {
    @Autowired
    @Lazy
    private Terminal terminal;

    public Clear() {
    }

    @ShellMethod(value = "Clear the shell screen.")
    public void clear() {
        this.terminal.puts(Capability.clear_screen, new Object[0]);
    }

    public interface Command {
    }
}
