package ch03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: TimeLock
 * @description: 限时等待锁
 * @author: zzy
 * @date: 2018-11-05 15:23
 * @version: V1.0
 **/
public class TimeLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            // 由于占用锁的线程会持有锁长达6秒，
            // 故另一个线程无法在5秒的等待时间内获得锁，
            // 因此，请求锁会失败。
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start(); t2.start();
    }
}
