package com.lh.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * 死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种相互等待的现象，
 * 如果无外力的干涉那它们都将无法推进下去，如果系统的资源充足，
 * 进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
 *
 * @author huanliu7
 * @date 2019/7/23 16:00
 */
public class DeadLockDemo2 {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        DeadLockDemo2 deadLockDemo = new DeadLockDemo2();
        executorService.execute(()->deadLockDemo.method(lockA, lockB));
        executorService.execute(()->deadLockDemo.method(lockB, lockA));
    }

    private void method(String lockA, String lockB) {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 获取到：" + lockA + " 尝试获得： " + lockB);
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
