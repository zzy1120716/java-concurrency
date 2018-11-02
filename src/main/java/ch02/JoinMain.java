package ch02;

/**
 * @ClassName: JoinMain
 * @description: join示例
 * @author: zzy
 * @date: 2018-11-02 10:18
 * @version: V1.0
 **/
public class JoinMain {

    public volatile static int i = 0;
    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
