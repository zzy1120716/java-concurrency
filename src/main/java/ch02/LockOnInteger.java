package ch02;

/**
 * @ClassName: LockOnInteger
 * @description: 错误的加锁（修改）将同步的对象从变量改为实例
 * @author: zzy
 * @date: 2018-11-05 11:18
 * @version: V1.0
 **/
public class LockOnInteger implements Runnable {
    public static Integer i = 0;
    static LockOnInteger instance = new LockOnInteger();
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }
}
