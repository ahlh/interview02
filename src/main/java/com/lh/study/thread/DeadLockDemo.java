package com.lh.study.thread;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 自己持有：" + lockA + " 尝试获得： " + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("获取到两把锁!");
            }
        }

    }
}

/**
 * 死锁
 * 死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种相互等待的现象，
 * 如果无外力的干涉那它们都将无法推进下去，如果系统的资源充足，
 * 进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
 * @author huanliu7
 * @date 2019/7/23 16:00
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB)).start();
        new Thread(new HoldLockThread(lockB, lockA)).start();

    }

}
