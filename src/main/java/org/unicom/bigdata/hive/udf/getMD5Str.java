//package org.koudai.bigdata.hive.udf;
//
//import org.apache.hadoop.hive.ql.exec.UDF;
//
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import static org.apache.logging.log4j.core.util.NameUtil.md5;
//
//*
// * @description 加密MD5算法自定义函数---hive自带MD5函数,故不需要自定义
// * @Auther: 韩金铭 1329674322@qq.com
// * @Date: 2018/10/18 21:43
//
//
//public class getMD5Str extends UDF {
//    public static String evaluate(String str) {
//        MessageDigest messageDigest = null;
//        try {
//            messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.reset();
//            //这里底层调用了engineUpdate方法，此方法进行一个更新操作。
//            // 然后state属性的状态就被改变了，表明当前计算正在处理过程中。
//            messageDigest.update(str.getBytes("UTF-8"));
//        } catch (NoSuchAlgorithmException e) {
//            System.out.println("NoSuchAlgorithmException caught!");
//            System.exit(-1);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        //在次摘要之后,摘要将重置状态
//        byte[] byteArray = messageDigest.digest();
//
//        StringBuffer md5StrBuff = new StringBuffer();
//        for (int i = 0; i < byteArray.length; i++) {
//            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
//                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
//            else
//                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
//        }
//        return md5(str);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(evaluate("韩金铭"));
//    }
//}
