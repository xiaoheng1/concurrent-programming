package day01;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSpeedTest {

    @Test
    public void test() throws InterruptedException {
        long[] counts = new long[]{100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L};
        for(int i=0; i<counts.length; i++){
            multiThreadAndSingleThreadSpeed(counts[i]);
            System.out.println("-------------------------");
        }
    }

    private void multiThreadAndSingleThreadSpeed(long count) throws InterruptedException {
        long start = System.currentTimeMillis();
        int b=0;
        for(long i=0; i<count; i++){
            b++;
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程耗时: " + (end-start) * 1.0 / 1000);


        start = System.currentTimeMillis();
        final long mid = count / 2;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a=0;
                for (int i = 0; i < mid; i++) {
                    a++;
                }
            }
        });
        thread.start();
        int c=0;
        for(long i=mid; i<count; i++){
            c++;
        }
        end = System.currentTimeMillis();
        thread.join();
        System.out.println("多线程耗时: " + (end-start) * 1.0 / 1000);
    }
}
