package utilTest;

import com.ontg.demo.UtilMain;
import com.ontg.demo.Utils.RestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UtilMain.class)
public class RestUtilTest {


    @Test
    public void m(){
//        resp = RestUtl.doGet("http://"+node.getHost() + "/comonitor/dataCollection", seachBody, Resp.class);
        String ssssss = RestUtil.doGet("https://www.baidu.com/", "ssssss", String.class);
        System.out.println(ssssss);
    }

}
