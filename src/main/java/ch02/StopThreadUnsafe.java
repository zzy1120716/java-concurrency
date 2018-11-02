package ch02;

/**
 * @ClassName: StopThreadUnsafe
 * @description: 模拟stop()强行终止线程
 * @author: zzy
 * @date: 2018-11-01 16:40
 * @version: V1.0
 **/
public class StopThreadUnsafe {

    public static User u = new User();
    private static class User {
        private int id;
        private String name;
        public User() {
            id = 0;
            name = "0";
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 省略setter和getter方法
        @Override
        public String toString() {
            return "User [" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ']';
        }
    }

    public static class ChangeObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    int v = (int)(System.currentTimeMillis() / 1000);
                    u.setId(v);
                    // Oh, do something else
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
