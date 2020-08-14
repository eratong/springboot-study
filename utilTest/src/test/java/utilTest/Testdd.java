package utilTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Testdd {

    private List<Tong> userlist = new ArrayList<>();

    @Test
    public void s() {
        while (true) {
            userlist.add(new Tong());
        }
    }
}

class Tong {
    Byte[] name = new Byte[1000000000];
}

/**
 * 别执行 严重内存溢出 GC overhead limit exceeded
 */
class Wrapper {
    public static void main(String args[]) throws Exception {
        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }
}

