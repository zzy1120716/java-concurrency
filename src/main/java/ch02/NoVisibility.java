package ch02;

/**
 * @ClassName: NoVisibility
 * @description: volatile保证可见性和有序性
 * @author: zzy
 * @date: 2018-11-02 10:33
 * @version: V1.0
 **/
public class NoVisibility {
    // 告诉JVM，这个变量可能会在不同线程中修改
    private volatile static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
