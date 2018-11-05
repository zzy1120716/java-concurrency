package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: FairLock
 * @description: 公平锁
 * @author: zzy
 * @date: 2018-11-05 17:39
 * @version: V1.0
 **/
public class FairLock implements Runnable {
    // 传入参数true，表示申请一个公平锁
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thread_t1");
        Thread t2 = new Thread(r1, "Thread_t2");
        t1.start(); t2.start();
    }
}
