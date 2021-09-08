import entity.TemplateVars;
import util.CodeScan;
import util.FileGenerator;

import java.io.IOException;
import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/20 16:08
 */
public class ShellGenerator {
    public static void main(String[] args) {
        // TODO: 修改path，服务端项目目录
        String path = "C:\\Users\\32858\\Desktop\\neeq\\tradingDemo";
        // TODO: 修改targetPath，ubsShell项目生成目录
        String targetPath = "C:\\Users\\32858\\Desktop\\neeq\\ubsShell\\src\\main\\java\\com\\neeq\\ubsshell\\shelldemo".replace(".", "\\");
        try {
            // 获取信息
            List<TemplateVars> templateVarsList = CodeScan.dirScan(path);
            // 为每个接口创建shell类，并生成文件
            for(TemplateVars templateVars : templateVarsList) {
                FileGenerator.javaGene(templateVars, targetPath);
            }
        } catch (IOException e) {
            System.err.println("路径错误");
            e.printStackTrace();
        }
    }
}
