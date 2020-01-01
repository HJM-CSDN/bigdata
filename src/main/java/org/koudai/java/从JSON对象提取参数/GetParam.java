package org.koudai.java.从JSON对象提取参数;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @description 从json对象中提取参数
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/2/5 22:28
 */
public class GetParam {
    public static void main(String[] args) {
        String constant = "{\"startAge\":[\"10\"],\"endAge\":[\"100\"],\"startDate\":[\"2018-12-19\"],\"endDate\":[\"2018-12-19\"],\"sex\":[\"female\"]}";
        JSONObject jsonObject = JSONObject.parseObject(constant);//通过String字符串生成jsonObject的方法
        System.out.println(getParam(jsonObject, "endAge"));
    }
    public static String getParam(JSONObject jsonObject, String field){
        JSONArray jsonArray = jsonObject.getJSONArray(field);
       // System.out.println(jsonArray);
        if (jsonArray != null && jsonArray.size() > 0){
            return jsonArray.getString(0);            
        }
        return null;
    }
}
