package org.unicom.java.线程池;

import java.util.concurrent.*;

/**
 * @description java的线程池支持主要通过ThreadPoolExecutor来实现
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/15 17:00
 */

/** https://www.jianshu.com/p/f030aa5d7a28
 * step1.调用ThreadPoolExecutor的execute提交线程，首先检查CorePool，如果CorePool内的线程小于CorePoolSize，新创建线程执行任务。
 * step2.如果当前CorePool内的线程大于等于CorePoolSize，那么将线程加入到BlockingQueue。
 * step3.如果不能加入BlockingQueue，在小于MaxPoolSize的情况下创建线程执行任务。
 * step4.如果线程数大于等于MaxPoolSize，那么执行拒绝策略。
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

    }
    public static ExecutorService newFixedThreadPool(int threads){
        /**各个参数的含义
         * 序号   名称          类型          含义
         * 1    corePoolSize    int         核心线程池大小
         * 2    maximumPoolSize int         最大线程池大小
         * 3    keepAliveTime   long        线程最大空闲时间
         * 4    unit            TimeUnit    时间单位
         * 5    workQueue  BlockingQueue<Runnable> 线程等待队列
         * 6    threadFactory    ThreadFactory    线程创建工厂
         * 7    handler  RejectedExecutionHandler 拒绝策略
         */
        return new ThreadPoolExecutor(threads,threads,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
        //corePoolSize与maximumPoolSize相等，即其线程全为核心线程，
        // 是一个固定大小的线程池，是其优势；
    }

    public static ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        /**
         * corePoolSize = 0，maximumPoolSize = Integer.MAX_VALUE，即线程数量几乎无限制；
         * keepAliveTime = 60s，线程空闲60s后自动结束。
         * workQueue 为 SynchronousQueue 同步队列，这个队列类似于一个接力棒，入队出队必须同时传递，
         * 因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue；
         *
         * 适用场景：快速处理大量耗时较短的任务，如Netty的NIO接受请求时，可使用CachedThreadPool。
         */
    }

}
