import com.ontg.redis.Exception.RedisCustomExecption;
import com.ontg.redis.UnifiedMain;
import io.lettuce.core.RedisException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UnifiedMain.class)
public class Test1 {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void cc() {
        BoundValueOperations test = redisTemplate.boundValueOps("test");
    }

    @Test
    public void m() {

        try {
            BoundValueOperations test = redisTemplate.boundValueOps("test");
            test.set("test");
        } catch (Exception e) {


            if (e instanceof RedisCustomExecption) {
                //redis 异常处理
                System.out.println("error");
            } else {
                System.out.println(e.getMessage());
            }

        }
    }

    @Test
    public void s() {
        Object result = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(org.springframework.data.redis.connection.RedisConnection connection) throws DataAccessException {
                return null;
            }
        });
        new RedisException("");
    }
}
