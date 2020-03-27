package org.unicom.bigdata.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @description 产生行号的UDF
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/12 19:34
 */
public class RowNumberUDF extends UDF {
    private int rowNumber = 1;
    public  int evaluate(String string){
        return this.rowNumber ++;
    }

}
