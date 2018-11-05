package ch02;

/**
 * @ClassName: AccountingSync2
 * @description: synchronized关键字，保证线程安全，另一种形式，与前一种等价
 * @author: zzy
 * @date: 2018-11-05 10:30
 * @version: V1.0
 **/
public class AccountingSync2 implements Runnable {

    static AccountingSync2 instance = new AccountingSync2();
    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
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
