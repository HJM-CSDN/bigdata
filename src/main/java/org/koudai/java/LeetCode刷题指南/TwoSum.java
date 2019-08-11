package org.koudai.java.LeetCode刷题指南;

import java.util.HashMap;
import java.util.Map;


/**
 * @description 两数之和
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/25 20:23
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer index = map.get(target - nums[i]);
            if(index == null){
                map.put(nums[i],i);
/*               Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<Integer,Integer> entry = iterator.next();
                    System.out.println(entry.getKey()+"\t"+entry.getValue());
                }*/
            }else{
                return new int[]{i,index};
            }

        }
        return new int[]{0,0};
    }

    public static void main(String[] args) {
        int[] nums = {2,7,3,10};
        int[] index = twoSum(nums,9);
        for (int i : index) {
            System.out.print(i+"\t");
        }
    }
}

//暴力解法 O(N^2)：
//
//嵌套两层循环：第一层：i 从 0 到 n - 2；第二层：j 从 i + 1 到 n - 1；
// 判断 nums[i] + nums[j] == target ，如果成立则是正确答案

//map解法 O(N*logN)：
//
//从 0 到 n - 1 依次遍历，利用map存放每一个数值的下标，在map中寻找是否有使（nums[i] + x == target）成立的x的存在，
// 如果存在则返回i和它的下标（即myMap[ target - nums[i] ])。
//
//复杂度分析：因为只遍历了一次数组，map每次的查询的时间复杂度为O(logN)所以整体复杂度为O(N*logN)、
// 如果这里使用hash_map可以将查询复杂度降低到O(1)，从而使得整体复杂度为O(N)，