package ch02;

/**
 * @ClassName: DaemonDemo
 * @description: 守护线程
 * @author: zzy
 * @date: 2018-11-02 10:47
 * @version: V1.0
 **/
public class DaemonDemo {

    public static class DaemonT extends Thread {
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
