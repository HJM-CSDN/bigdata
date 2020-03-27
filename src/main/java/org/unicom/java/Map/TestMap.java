package org.unicom.java.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description Map的四种遍历方式
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/18 20:59
 */
public class TestMap {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"abc");
        map.put(4,"abcd");
        System.out.println(map.size());//4
        System.out.println(map.get(4));//abcd 会覆盖

        /**
         * 第一种遍历方式：
         *      Set<Integer>set = map.keySet() 得到所有key的集合
         *      for(Integer in : set) String str = map.get(in)
         */
        for (Integer integer : map.keySet()) {
            String string = map.get(integer);
            System.out.println("key:"+integer+" value:"+string);
        }

        /**
         * 第二种遍历方式
         *      entrySet
         */
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, String> entry = it.next();
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }

        /**
         * 第三种遍历方式
         *      推荐，尤其是容量大时
         */
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }

        /**
         * 第四种遍历方式
         *      通过map.values遍历所有的values，但是不能遍历key
         */
        for (String value : map.values()) {
            System.out.println("value:"+value);
        }

    }
}
