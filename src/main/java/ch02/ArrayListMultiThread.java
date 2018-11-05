package ch02;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @ClassName: ArrayListMultiThread
 * @description: 并发
 * @author: zzy
 * @date: 2018-11-05 10:42
 * @version: V1.0
 **/
public class ArrayListMultiThread {
    // 改进方法：使用线程安全的Vector代替ArrayList
    // static ArrayList<Integer> al = new ArrayList<Integer>(10);
    static Vector<Integer> al = new Vector<Integer>(10);
    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(al.size());
    }
}
