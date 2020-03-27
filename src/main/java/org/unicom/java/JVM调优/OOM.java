package org.unicom.java.JVM调优;

/**
 * @description 发生OOM异常的代码
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/23 15:11
 */
public class OOM {
    public static void main(String[] args) {
        String string = "OOM";
        while (true){
            string += string + "Out Of Memory Error";//在堆中不停地实例化对象
        }
    }
}
