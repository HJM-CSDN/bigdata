package org.unicom.java.过滤器;

import junit.framework.Assert;

/**
 * @description 布隆过滤器
 *         BloomFilter的核心思想有两点：
 *      1. 多个hash，增大随机性，减少hash碰撞的概率
 *      2. 扩大数组范围，使hash值均匀分布，进一步减少hash碰撞的概率。
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2017/12/14
 */
//需求场景:如何判断一个元素在亿级数据中是否存在?
public class BloomFilter {
    /**
     * 数组长度
     */
    private int arraySize;
    /**
     * 数组
     */
    private int[] array;

    public BloomFilter(int arraySize){
        this.arraySize = arraySize;
        array = new int[arraySize];
    }
    //写入数据
    public void add(String key){
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    /**
     * 判断数据是否存在
     * @param key
     * @return
     */
    public boolean check(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }

        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }

        int thirdIndex = array[third % arraySize];
        if (thirdIndex == 0) {
            return false;
        }

        return true;

    }

    /**
     * hash算法1
     */
    private int hashcode_1(String key){
        int hash = 0;
        int i;
        for(i = 0;i<key.length();++i){
            hash = 33*hash + key.charAt(i);
        }
        return Math.abs(hash);
    }
    /**
     * hash 算法2
     * @param data
     * @return
     */
    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     *  hash 算法3
     * @param key
     * @return
     */
    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }

    public static void main(String[] args) {
        long star = System.currentTimeMillis();
        BloomFilter bloomFilters = new BloomFilter(10000000) ;
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "") ;
        }
        //assert函数:断言,判断Boolean值是否为true或者false,判断错误会输出错误信息,判断正确则继续执行.
        Assert.assertTrue(bloomFilters.check(1+""));
        Assert.assertTrue(bloomFilters.check(2+""));
        Assert.assertTrue(bloomFilters.check(3+""));
        Assert.assertTrue(bloomFilters.check(9999999+""));

        Assert.assertFalse(bloomFilters.check(400230340+""));
        System.out.println(bloomFilters.check(1234567+""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}

/**扩展:
 * redis缓存击穿：
 *   查询一个必然不存在的数据。比如文章表，查询一个不存在的id，每次都会访问DB，
 *    如果有人恶意破坏，很可能直接对DB造成影响。
 * 解决办法：
 *   将数据库中所有的查询条件，放到布隆过滤器中。当一个查询请求来临的时候，
 *    先经过布隆过滤器进行检查，如果请求存在这个条件中，那么继续执行，如果不在，直接丢弃。
 * ---------------------
 * 我们可以利用布隆过滤器，将redis缓存击穿控制在一个可容忍的范围内。
 */
