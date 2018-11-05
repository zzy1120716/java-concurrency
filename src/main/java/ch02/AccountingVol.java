package ch02;

/**
 * @ClassName: AccountingVol
 * @description: volatile关键字不能保证线程安全
 * @author: zzy
 * @date: 2018-11-05 10:25
 * @version: V1.0
 **/
public class AccountingVol implements Runnable {

    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;
    public static void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; i < 10000000; j++) {
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
