package org.unicom.java.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description 获取各种时间
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2017/11/23 21:27
 * https://blog.csdn.net/u011019141/article/details/78686985/
 */
public class DateUtil {
    public static void main(String[] args) {
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        System.out.println("Current Date: " + cale.getTime());
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        System.out.println("Day of Week: " + dow);
        System.out.println("Day of Month: " + dom);
        System.out.println("Day of Year: " + doy);

        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);

        // 获取当前日期字符串
        Date d = new Date();
        System.out.println("当前日期字符串1：" + format.format(d));
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "
                + hour + ":" + minute + ":" + second);


        Date date = new Date();
        System.out.println(date);

        long time = System.currentTimeMillis();
        System.out.println(time);

        Date date1 = new Date(time);
        System.out.println(date1);

        System.out.println(date1.getTime());


        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");//12小时制
        System.out.println(format2.format(date));
        System.out.println(format3.format(date));

        SimpleDateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmssSSS");//24小时制

        try {
            System.out.println((format4.parse("20170412030030017")));
            //Wed Apr 12 03:00:30 CST 2017
            System.out.println((format4.parse("20170412030030017")).getTime());
            //结果:1491937230017
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
