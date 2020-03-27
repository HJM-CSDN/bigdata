package org.unicom.bigdata.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.Calendar;


/**
 * @description 输入年月日输出其年龄和星座
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/23 21:01
 */
public class AgeAndConstellation extends UDF {

    public static String evaluate(String data){
        String[] items = data.split("-");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int age = year - Integer.parseInt(items[0]);

        String birthdaystring = items[1] +"."+ items[2];
        double birthday = Double.parseDouble(birthdaystring);

        String result = null;

        if (birthday>=3.21 && birthday<=4.19){
            result = "白羊座";
        }else if (birthday>=4.20 && birthday<=5.20){
            result = "金牛座";
        }else if (birthday>=5.21 && birthday<=6.21){
            result = "双子座";
        }else if (birthday>=6.22 && birthday<=7.22){
            result = "巨蟹座";
        }else if (birthday>=7.23 && birthday<=8.22){
            result = "狮子座";
        }else if (birthday>=8.23 && birthday<=9.22){
            result = "处女座";
        }else if (birthday>=9.23 && birthday<=10.23){
            result = "天秤座";
        }else if (birthday>=10.24 && birthday<=11.22){
            result = "天蝎座";
        }else if (birthday>=11.23 && birthday<=12.21){
            result = "射手座";
        }else if (birthday>=12.22 && birthday<=1.19){
            result = "摩羯座";
        }else if (birthday>=1.20 && birthday<=2.18){
            result = "水瓶座";
        }else if (birthday>=2.19 && birthday<=3.20){
            result = "双鱼座";
        }

        String result1 = data +" "+result+" "+age;
        return result1;
    }
    /**
     * 在Linux的hive里的操作
     */
/*//1.添加用户UDF的jar包
    hive>add jar /ageAndConsellation.jar;

    //2.创建临时函数
    create temporary function ageAndConstellation as 'org.qianfeng.bigdata.hive.udf.AgeAndConstellation';
    //3.测试是否添加好
    show functions;

    select ageandconstellation("1991-10-23");

    //4、确定无用时可以删除：
    drop temporary function ageandconstellation;

结果:
0: jdbc:hive2://> select ageandconstellation("1991-10-23");
OK
+--------------------+
|        _c0         |
+--------------------+
| 1991-10-23 天秤座 27  |
+--------------------+
1 row selected (0.236 seconds)
0: jdbc:hive2://> select ageandconstellation("1996-03-19");
OK
+--------------------+
|        _c0         |
+--------------------+
| 1996-03-19 双鱼座 22  |
+--------------------+
1 row selected (0.219 seconds)
0: jdbc:hive2://> select ageandconstellation("1996-10-02");
OK
+--------------------+
|        _c0         |
+--------------------+
| 1996-10-02 天秤座 22  |
+--------------------+
1 row selected (0.077 seconds)
0: jdbc:hive2://>


    */

    /**
      本地测试:
     */
    public static void main(String[] args) {
        String result = evaluate("1998-01-26");
        System.out.println(result);
    }
}
