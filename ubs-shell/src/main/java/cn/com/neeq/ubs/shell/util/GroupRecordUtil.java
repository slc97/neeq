package cn.com.neeq.ubs.shell.util;

import org.jline.utils.AttributedStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.CommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * @author 宋立成
 * @date 2021/8/13 15:28
 */
@Component
public class GroupRecordUtil {

    // 判断目录层级是否到实体类层
    private static boolean inGroup = false;

    // 存储当前目录
    private static List<String> thisGroup = new ArrayList<String>(Arrays.asList("ubsShell"));

    private static CommandRegistry commandRegistry;

    @Autowired
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    // 获取当前目录
    public static String getGroup() {
        if(thisGroup.isEmpty()) {
            return "/";
        }
        String res = "";
        for(String str : thisGroup) {
            res += "/" + str;
        }
        return res;
    }

    // 配合cd命令，添加子目录
    public static String setGroup(String group) {
        thisGroup.add(group);
        setInGroup(true);
        return getGroup();
    }

    // 配合cd命令，跳回上一级
    public static String deleteGroup() {
        if(thisGroup.size() == 1) {
            return "Sorry, this is the root context.";
        }
        thisGroup.remove(thisGroup.size()-1);
        setInGroup(false);
        return getGroup();
    }

    // 查看是否在实体类层级
    public static boolean isInGroup() {
        return inGroup;
    }

    // 设置是否在实体类层级
    public static void setInGroup(boolean inGroup) {
        GroupRecordUtil.inGroup = inGroup;
    }

    // 获取当前目录下的子目录
    public static List<String> getGroupList() {
        List<String> groups = new ArrayList<>();
        AttributedStringBuilder result = new AttributedStringBuilder();
        Map<String, MethodTarget> commandsByName = commandRegistry.listCommands();
        SortedMap<String, Map<String, MethodTarget>> commandsByGroupAndName = commandsByName
                .entrySet().stream()
                .collect(groupingBy(e -> e.getValue().getGroup(),
                        TreeMap::new,
                        toMap(Map.Entry::getKey, Map.Entry::getValue)));
        commandsByGroupAndName.forEach((group, commandsInGroup) -> {
            if (!group.equals("Built-In Commands")) {
                String[] strs = group.toLowerCase().split(" ");
                String str = strs[0];
                for (int i = 1; i < strs.length - 1; ++i) {
                    str += "_" + strs[i];
                }
                groups.add(str);
            }
        });
        return groups;
    }
}
