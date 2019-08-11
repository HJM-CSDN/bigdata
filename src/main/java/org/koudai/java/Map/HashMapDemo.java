package org.koudai.java.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description 利用HashMap结构统计字符串中字母出现的个数
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/21 19:49
 */
public class HashMapDemo {
    public static void main(String[] args) {
        String string = "adfafdgaslfjasdfasfasfasfasfsadslgjasof";
        HashMap<Character,Integer> hashMap = new HashMap<Character,Integer>();

        for (int i = 0;i < string.length();i++){
            char c = string.charAt(i);
            if (!hashMap.containsKey(c)){
                hashMap.put(c,1);
            }else {
                hashMap.put(c,hashMap.get(c)+1);
            }
        }

        /**
         * 通过Set<K> KeySet()遍历
         * 得到所有的key,存放在一个Set中.利用set的迭代器遍历得到key,在利用key得到value
         */
        Set<Character> set = hashMap.keySet();
        Iterator<Character> iterator = set.iterator();
        while(iterator.hasNext()){
            Character key = iterator.next();
            System.out.println(key + "(" + hashMap.get(key) + ")");
        }
        System.out.println();


        /**
         * Set<Map.Entry<K,V>> entrySet() 遍历
         * 得到每个键值对对应的映射关系类型的对象(entry/实体),存放在一个Set中,利用set的迭代器遍历得到entry,
         * 再利用entry的方法得到value和key
         */
        // 先得到装着entry的set
        Set<Map.Entry<Character, Integer>> set2 = hashMap.entrySet();
        // 获取迭代器
        Iterator<Map.Entry<Character, Integer>> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator2.next();
            // 获取key，value
            System.out.println(entry.getKey() + "(" + entry.getValue() + ")");

        }
    }
}
