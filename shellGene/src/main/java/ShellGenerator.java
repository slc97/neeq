import javassist.bytecode.BadBytecode;
import util.CodeScan;

import java.io.IOException;

/**
 * @author 宋立成
 * @date 2021/8/20 16:08
 */
public class ShellGenerator {
    public static void main(String[] args) {
        String path = "C:\\Users\\32858\\Desktop\\tradingDemo";
        try {
            CodeScan.dirScan(path);
        } catch (IOException e) {
            System.err.println("路径错误");
            e.printStackTrace();
        }
    }
}
