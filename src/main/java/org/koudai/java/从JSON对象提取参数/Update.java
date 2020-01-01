package org.koudai.java.从JSON对象提取参数;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.util.ajax.JSON;

import static org.koudai.java.从JSON对象提取参数.GetParam.getParam;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/10/10 17:47
 */
public class Update {
    public static void main(String[] args) {
        String constant = "[{\"name\": \"test\", \"count\": 13, \"status\": 1, \"initCount\": null}, {\"name\": \"buy_product_count\", \"count\": 11, \"status\": 1, \"initCount\": null}]";
        Object parse = JSON.parse(constant);


        JSONObject jsonObject = JSONObject.parseObject(constant);//通过String字符串生成jsonObject的方法
        System.out.println(getParam(jsonObject, "count"));

/**
 * update kb_customer_counter set
 * count_value=JSON_SET(count_value,CONCAT(SUBSTR(SUBSTRING_INDEX(JSON_SEARCH(count_value,'one','test'),'.',1),2),'.count'),
 * JSON_EXTRACT(count_value,CONCAT(SUBSTR(SUBSTRING_INDEX(JSON_SEARCH(count_value,'one','test'),'.',1),2),'.count'))+1 )
 * where JSON_CONTAINS(count_value,JSON_OBJECT('name','test')) and
 * JSON_EXTRACT(count_value,CONCAT(SUBSTR(SUBSTRING_INDEX(JSON_SEARCH(count_value,'one','test'),'.',1),2),'.status'))=1;
 */


    }

}
