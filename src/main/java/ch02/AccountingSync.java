package ch02;

/**
 * @ClassName: AccountingSync
 * @description: synchronized关键字，保证线程安全
 * @author: zzy
 * @date: 2018-11-05 10:30
 * @version: V1.0
 **/
public class AccountingSync implements Runnable {

    static AccountingSync instance = new AccountingSync();
    static int i = 0;

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
