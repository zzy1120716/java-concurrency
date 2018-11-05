package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReenterLock
 * @description: 重入锁
 * @author: zzy
 * @date: 2018-11-05 11:27
 * @version: V1.0
 **/
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            // 加锁
            lock.lock();
            try {
                i++;
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock tl = new ReenterLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }
}
