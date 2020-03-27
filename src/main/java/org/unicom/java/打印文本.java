package org.unicom.java;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/12/1 17:23
 */
public class 打印文本 {
    public static void main(String[] args) throws Exception {
        String string = "孤独若不是由于内向，便往往是由于卓绝。\n太美丽的人感情容易孤独，太优秀的人心灵容易孤独。\n" +
                "其中的道理显而易见，因为他们都难以找到合适的伙伴。\n太阳是孤独的，月亮是孤独的，星星却难以数计。";
        char[] a = string.toCharArray();


        for (int i =0;i<a.length;i++){
            System.out.print(a[i]);
            if ('\n'==(a[i])){
                Thread.sleep(600);
            }
            Thread.sleep(200);
        }
    }
}
