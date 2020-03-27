package org.unicom.java.Runtime;

/**
 * @description Runtime
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/26 08:15
 */
public class RuntimeDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println(runtime.totalMemory()/1024./1024);
        System.out.println(runtime.freeMemory()/1024./1024);
        System.out.println(runtime.maxMemory()/1024./1024);
    }
}

