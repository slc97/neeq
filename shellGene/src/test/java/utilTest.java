import util.InfoProcessUtil;

/**
 * @author 宋立成
 * @date 2021-09-01 09:18
 */
public class utilTest {
    public static void main(String[] args) {
        String url = "{org.springframework.web.bind.annotation.RequestMethod.POST}";
        System.out.println(InfoProcessUtil.requestProcess(url));
    }
}
