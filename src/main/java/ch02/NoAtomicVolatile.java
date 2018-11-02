package ch02;

/**
 * @ClassName: NoAtomicVolatile
 * @description: volatile无法保证i++的原子性操作
 * 如果i++是原子性的，那么最终值应该是100000，但实际输出总是小于100000
 * @author: zzy
 * @date: 2018-11-02 10:29
 * @version: V1.0
 **/
public class NoAtomicVolatile {

    static volatile int i = 0;
    public static class PlusTask implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++)
                i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i ++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(i);
    }
}
