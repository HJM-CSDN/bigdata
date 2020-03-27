package org.unicom.java.JVM调优;

/**
 * @description JVM调优
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2016/2/23 14:53
 *   https://blog.csdn.net/ycd500756/article/details/81274450
 */
public class JVM {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        //System.gc(); //手动gc后eden占用率直接下降
        System.out.println("虚拟机试图使用的最大内存是："+maxMemory/(double)1024/1024+"MB");
        System.out.println("虚拟机总内存是："+totalMemory/(double)1024/1024+"MB");

    }
}
/**
 * IDEA 控制台输出JVM的GC日志
    1.选择 run→edit configurations
    2.在 VM options 添加 -XX:+PrintGCDetails 即可

 设置JVM参数
 -Xms8m -Xmx8m -XX:+PrintGCDetails
 参数是一次设置最大分配8M，初始分配也是8M，并且打印GC的信息
 */