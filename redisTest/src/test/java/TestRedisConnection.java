import com.ontg.demo.RedisMain;
import com.ontg.demo.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisMain.class)
public class TestRedisConnection {

    @Autowired
    private RedisService redisService;

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;

    @Test
    public void checkRedisConnectionJob(){
        redisService.checkConnection();

//        BoundValueOperations<String, String> t = stringRedisTemplate.boundValueOps("t");
//        t.set("test");
//
//        System.out.println(t.get());
    }
}
