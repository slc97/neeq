package com.neeq.ubsshell.shelldemo.builtInCommands;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.jline.reader.Parser;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.FileInputProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author 宋立成
 * @date 2021/8/17 10:29
 */
@ShellComponent
public class Script extends BuiltInCommand {
    private final Shell shell;
    private final Parser parser;

    public Script(Shell shell, Parser parser) {
        this.shell = shell;
        this.parser = parser;
    }

    @ShellMethod("Read and execute commands from a file.")
    public void script(File file) throws IOException {
        Reader reader = new FileReader(file);
        FileInputProvider inputProvider = new FileInputProvider(reader, this.parser);
        Throwable var4 = null;

        try {
            this.shell.run(inputProvider);
        } catch (Throwable var13) {
            var4 = var13;
            throw var13;
        } finally {
            if (inputProvider != null) {
                if (var4 != null) {
                    try {
                        inputProvider.close();
                    } catch (Throwable var12) {
                        var4.addSuppressed(var12);
                    }
                } else {
                    inputProvider.close();
                }
            }

        }

    }

    public interface Command {
    }
}
