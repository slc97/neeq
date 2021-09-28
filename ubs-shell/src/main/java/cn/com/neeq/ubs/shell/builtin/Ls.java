package cn.com.neeq.ubs.shell.builtin;

import cn.com.neeq.ubs.shell.util.GroupRecordUtil;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

import java.util.*;
import java.util.Map.Entry;

/**
 * 查看当前目录下可用命令
 *
 * @author 宋立成
 * @date 2021/8/16 8:43
 */
@ShellComponent
public class Ls extends BaseBuiltinCommand {

    private CommandRegistry commandRegistry;

//    private UserShell userShell;

    @Autowired
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @ShellMethod(value = "显示当前目录下的子目录及命令")
    public CharSequence ls() {
        // 获取当前目录可用命令
        return getCommands();
    }

    private CharSequence getCommands() {
        AttributedStringBuilder result = new AttributedStringBuilder();
        Map<String, MethodTarget> commandsByName = commandRegistry.listCommands();
        SortedMap<String, Map<String, MethodTarget>> commandsByGroupAndName = commandsByName
                .entrySet().stream()
                .collect(groupingBy(e -> e.getValue().getGroup(),
                        TreeMap::new,
                        toMap(Entry::getKey,Entry::getValue)));
        // 显示当前目录的路径
        result.append("当前目录：" + GroupRecordUtil.getGroup()).append("\n\n");

        // 显示当前目录下的子目录
        result.append("---AVAILABLE CONTENT---", AttributedStyle.BOLD).append("\n");
        if(!GroupRecordUtil.isInGroup()) {
            List<String> groups = GroupRecordUtil.getGroupList();
            for (String group : groups) {
                result.append(group + "   ");
            }
        }

        result.append("\n\n");

        // 显示当前目录下的可用命令
        result.append("---AVAILABLE COMMANDS---", AttributedStyle.BOLD).append("\n");
        commandsByGroupAndName.forEach((group, commandsInGroup) ->{
            if(group.equals("Built-In Commands")) {
                result.append(group, AttributedStyle.BOLD).append('\n');
                Map<MethodTarget, SortedSet<String>> commandNamesByMethod = commandsInGroup.entrySet().stream()
                        .collect(groupingBy(Entry::getValue, mapping(Entry::getKey, toCollection(TreeSet::new))));
                commandNamesByMethod.entrySet().stream().sorted(sortByFirstCommandName()).forEach(e -> {
                    result.append(isAvailable(e.getKey()) ? e.getValue()+"   " : "");
                });
                result.append("\n\n");
                result.append("Common Commands",AttributedStyle.BOLD).append('\n');
            }
            else {
                Map<MethodTarget, SortedSet<String>> commandNamesByMethod = commandsInGroup.entrySet().stream()
                        .collect(groupingBy(Entry::getValue, mapping(Entry::getKey, toCollection(TreeSet::new))));
                commandNamesByMethod.entrySet().stream().sorted(sortByFirstCommandName()).forEach(e -> {
                    result.append(isAvailable(e.getKey()) ? e.getValue() + "   " : "");
                });
            }
        });
        if (commandsByName.values().stream().distinct().anyMatch(m -> !isAvailable(m))) {
            result.append("\n\nType `help <command>` to learn more.\n");
        }
        return result;
    }

    private Comparator<Entry<MethodTarget, SortedSet<String>>> sortByFirstCommandName() {
        return Comparator.comparing(e -> e.getValue().first());
    }

    private boolean isAvailable(MethodTarget methodTarget) {
        return methodTarget.getAvailability().isAvailable();
    }


}
