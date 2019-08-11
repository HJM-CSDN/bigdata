package org.koudai.java.有趣的算法;

/**
 * @description 给定一个非空整数数组，除了某个元素只出现一次以外，其余元素每个局出现两次，
 * 找出只出现一次的元素
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/18 18:51
 */
public class SearchNumber {
    public static void main(String[] args) {
        int[] arr = {2,2,3,5,3,6,6};
        System.out.println(solution(arr));
    }
    public static int solution(int[] arr){
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
            /**
             * 这里用异或的方法，更加方便，减少空间占用，时间复杂度为O（n）
             */
        }
        return res;
    }

}
