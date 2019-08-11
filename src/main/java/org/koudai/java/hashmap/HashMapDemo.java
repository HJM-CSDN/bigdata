package org.koudai.java.hashmap;

import java.util.Collection;
import java.util.HashMap;

/**
 * @description hashMap
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/26 14:01
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("1","提莫");
        hashMap.put("2","卡莎");
        hashMap.put("4","诺克");
        hashMap.put("3","皇子");

        Collection values = hashMap.values();
        System.out.println(values);

    }
}
