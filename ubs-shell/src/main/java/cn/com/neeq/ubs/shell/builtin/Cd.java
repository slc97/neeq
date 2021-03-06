package cn.com.neeq.ubs.shell.builtin;

import cn.com.neeq.ubs.shell.util.GroupRecordUtil;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/13 15:16
 */
@ShellComponent
public class Cd extends BaseBuiltinCommand {
    @ShellMethod(value = "目录跳转")
    public String cd(String content) {
        if(content.equals("..")) {
            return GroupRecordUtil.deleteGroup();
        }
        if(GroupRecordUtil.isInGroup()) {
            return "No this content. Type 'ls' to learn more.";
        }
        List<String> groups = GroupRecordUtil.getGroupList();
        boolean flag = false;
        for(String group : groups) {
            if(group.equals(content)) {
                flag = !flag;
                break;
            }
        }
        if(!flag) {
            throw new IllegalArgumentException("Unkonwn content '" + content + "'");
        }
        GroupRecordUtil.setGroup(content);
        return "当前目录为" + GroupRecordUtil.getGroup();
    }
}
