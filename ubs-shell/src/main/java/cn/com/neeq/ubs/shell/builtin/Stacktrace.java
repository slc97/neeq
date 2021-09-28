package cn.com.neeq.ubs.shell.builtin;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.result.ThrowableResultHandler;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author 宋立成
 * @date 2021/8/17 10:32
 */
@ShellComponent
public class Stacktrace {
    @Autowired
    @Lazy
    private Terminal terminal;
    @Autowired
    private ThrowableResultHandler throwableResultHandler;

    public Stacktrace() {
    }

    @ShellMethod(
            key = {"stacktrace"},
            value = "Display the full stacktrace of the last error."
    )
    public void stacktrace() {
        if (this.throwableResultHandler.getLastError() != null) {
            this.throwableResultHandler.getLastError().printStackTrace(this.terminal.writer());
        }

    }

    public interface Command {
    }
}

