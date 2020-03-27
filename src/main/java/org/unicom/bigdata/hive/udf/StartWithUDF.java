package org.unicom.bigdata.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @description 判断一个给定字符串是否是原字符串的开头
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/12 19:36
 */
public class StartWithUDF extends UDF {
    public boolean evaluate(String sourceStr,String start){
        return sourceStr.startsWith(start);
    }
}
