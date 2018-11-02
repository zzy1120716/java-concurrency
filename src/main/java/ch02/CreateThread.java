package ch02;

/**
 * @ClassName: CreateThread
 * @description: 使用接口来定义Thread
 * @author: zzy
 * @date: 2018-11-01 16:29
 * @version: V1.0
 **/
public class CreateThread implements Runnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new CreateThread());
        t1.start();
    }

    @Override
    public void run() {
        System.out.println("Oh, I am Runnable");
    }
}
