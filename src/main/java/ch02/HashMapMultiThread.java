package ch02;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: HashMapMultiThread
 * @description: 并发
 * @author: zzy
 * @date: 2018-11-05 10:47
 * @version: V1.0
 **/
public class HashMapMultiThread {

    // 最简单的解决方案就是使用ConcurrentHashMap代替HashMap
    // static Map<String, String> map = new HashMap<String, String>();
    static Map<String, String> map = new ConcurrentHashMap<String, String>();

    public static class AddThread implements Runnable {
        int start = 0;
        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(map.size());
    }
}
