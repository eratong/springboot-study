package utilTest;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    @Test
    public void sn() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 10; i++) {
                        System.out.println("runRedisHeart try " + i + " time-----");

                        if (i > 10) {
                            //优雅关闭程序
                            timer.cancel();
                        }
                        //尝试重连
                        System.out.println("try");
//                        try {
//                            System.out.println("try");
//                          throw  new Exception("error");
////                            timer.cancel();//重连上了
//                        } catch (Exception e) {
//                            System.out.println("redis reconnect error-----"+e.getMessage());
//                        }
//                        System.out.println("ssssss");
//                        Thread.sleep(60000);
                    }
                } catch (Exception e) {
                    System.out.println("runRedisHeart error" + e.getMessage());
                }
            }
        };
        //执行
        timer.schedule(timerTask, 0);
    }


    @Test
    public void xs() {
        sn();
//        try {
//            sn();
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    @Test
    public void ssss() {
        System.out.println("0");
        try {
            throw new Exception("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("1");
    }


    @Test
    public void s() {
        Timer timer = new Timer();
        System.out.println(Thread.currentThread().getName());

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i > 10) {
                            timer.cancel();
                        }
                        System.out.println("执行了。。。。");
                        Thread.currentThread().sleep(3000);
                    }

//                    java.util.concurrent.TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0);
    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(Thread.currentThread().getName());

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                try {
                    for (int i = 0; i < 10; i++) {
                        if (i > 10) {
                            timer.cancel();
                        }
                        System.out.println("执行了。。。。");
                        Thread.currentThread().sleep(3000);
                    }

//                    java.util.concurrent.TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(timerTask, 0);
    }
//public static void main(String[] args) {
//    Timer timer = new Timer();
//
//    TimerTask timerTask = new TimerTask() {
//        @Override
//        public void run() {
//            int i = 0;
//            while (i < 10) {
//                i++;
//                System.out.println("Hello World!");
//                if (i == 10) {
//                    timer.cancel();
//                }
//            }
//
//        }
//    };
//
//    timer.schedule(timerTask, 6000);
//}
//
}
