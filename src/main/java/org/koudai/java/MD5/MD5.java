package org.koudai.java.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @description 数据加密算法MD5输入密码例子
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/18 20:40
 */
public class MD5 {
    public static void main(String[] args) {

//        String password = getMD5Str("韩金铭");
//        System.out.println("请输入密码:");
//        Scanner scanner = new Scanner(System.in);
//        String string = scanner.next();
//        if (password.equals(getMD5Str(string))){
//            System.out.println("输入正确");
//        }else{
//            System.out.println("输入错误");
//        }

        String word = getMD5Str("韩金铭");
        System.out.println(word);
        word = getMD5Str("韩金铭");
        System.out.println(word);

    }

    /**
     * 方法一
     * @param str
     * @return
     */
    public static  String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            //这里底层调用了engineUpdate方法，此方法进行一个更新操作。
            // 然后state属性的状态就被改变了，表明当前计算正在处理过程中。
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //在次摘要之后,摘要将重置状态
        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

}
