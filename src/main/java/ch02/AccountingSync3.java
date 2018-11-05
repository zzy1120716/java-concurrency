package ch02;

/**
 * @ClassName: AccountingSync3
 * @description: synchronized关键字作用于静态方法
 * @author: zzy
 * @date: 2018-11-05 10:30
 * @version: V1.0
 **/
public class AccountingSync3 implements Runnable {

    static int i = 0;

    // 将synchronized关键字作用于静态方法
    // 虽然两个线程指向不同的Runnable对象，但由于方法块需要请求的是当前类的锁，
    // 而非当前实例，因此，线程间还是可以正确同步。
    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSync3());
        Thread t2 = new Thread(new AccountingSync3());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }
}
