package com.lh.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池使用过吗？谈谈对 ThreadPoolExector 的理解？
 * <p>
 * 为什使用线程池，线程池的优势？
 * <p>
 * 线程池用于多线程处理中，它可以根据系统的情况，可以有效控制线程执行的数量，优化运行效果。
 * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列，
 * 然后在线程创建后启动这些任务，如果线程数量超过了最大数量，那么超出数量的线程排队等候，
 * 等其它线程执行完毕，再从队列中取出任务来执行。
 * <p>
 * 主要特点为：
 * <p>
 * - 线程复用
 * - 控制最大并发数量
 * - 管理线程
 * <p>
 * 主要优点
 * <p>
 * - 降低资源消耗，通过重复利用已创建的线程来降低线程创建和销毁造成的消耗。
 * - 提高相应速度，当任务到达时，任务可以不需要的等到线程创建就能立即执行。
 * - 提高线程的可管理性，线程是稀缺资源，如果无限制的创建，不仅仅会消耗系统资源，
 *      还会降低体统的稳定性，使用线程可以进行统一分配，调优和监控。
 *
 * @author huanliu7
 * @date 2019/7/23 11:24
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); //一池5线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一池n线程
        try {
            for (int i = 1; i <= 10; i++) {
                //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会儿线程,可以看到newCachedThreadPool效果
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
