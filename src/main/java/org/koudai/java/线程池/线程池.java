package org.koudai.java.线程池;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @description 创建线程池的四种方式
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/2/18 21:29
 */
public class 线程池 {
    public static void main(String[] args) {
        /**
         * 1.newCachedThreadPool
         * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，
         * 若无可回收，则新建线程，要注意控制job数量，太多会系统瘫痪
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0;i<10;i++){
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }

        /**
         * 2 newFixedThreadPool
         * 创建一个指定工作线程数量的线程池，每当提交一个任务就创建一个工作线程，如果工作线程数量达到
         * 线程池出事的最大数，则将提交的任务存入池队列中。
         * 优点：线程池提高程序效率和节省创建线程时所耗的开销
         * 缺点：线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i<10;i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * 3 newSingleThreadExecutor
         * 创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，
         * 保证所有任务按照指定顺序(FIFO, LIFO,优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。
         * 单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i<10;i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(2001);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * 4 newSchedulerThreadPool
         * 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
         * 延迟3秒执行，延迟执行示例代码如下：
         */
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3, TimeUnit.SECONDS);
    }
}

