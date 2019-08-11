package org.koudai.java.排序算法大全;

/**
 * @description 睡眠排序
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/12/7 14:04
 */
public class SleepSort {
    public static void main(String[] args) {
        // 构造一个乱序的数组
        int[] arr = {4,3,24,6,2,56,23,235,11,22,33,44,234};
        // 创建一个线程数组
        SortThread[] sortThreads = new SortThread[arr.length];
        // 循环取出待排序的数值,传入每一个线程
        for (int i = 0; i < sortThreads.length; i++) {
            sortThreads[i] = new SortThread(arr[i]);
        }
        // 挨个启动线程,线程马上进入睡眠
        for (int i = 0; i < sortThreads.length; i++) {
            sortThreads[i].start();
        }
    }
}
class SortThread extends Thread{
    private int second;
    public SortThread(int s){
        this.second = s;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(second*10+10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(second+"\t");
    }
}