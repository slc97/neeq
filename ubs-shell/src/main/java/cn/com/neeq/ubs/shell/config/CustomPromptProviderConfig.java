package cn.com.neeq.ubs.shell.config;

import cn.com.neeq.ubs.shell.shelldemo.UserShell;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动信息
 *
 * @author 宋立成
 * @date 2021/8/16 16:43
 */
@Component
public class CustomPromptProviderConfig implements PromptProvider {

    @Autowired
    private UserShell userShell;

    @Override
    public AttributedString getPrompt() {
        AttributedStyle promotStyle = AttributedStyle.BOLD;
        String hostName = getHostName();
        String pre = "Welcome to neeq ubs shell.\nPlease login first with command 'neeq [-username] [-password]'.\n";
        String promot = "ubsShell@" + hostName + ">";
        if(!userShell.isConnected()) {
            return new AttributedString(pre + promot, promotStyle);
        }

        // 获取用户名
        String username = userShell.getUsername();
        promot = "ubsShell@" + username + ">";

        return new AttributedString(promot, promotStyle);
    }

    private String getHostName(){
        String hostName = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
            inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
