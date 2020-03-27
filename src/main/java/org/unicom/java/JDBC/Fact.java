package org.unicom.java.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2020/1/4 10:25
 */
public class Fact {
    private static final String name = "root";
    private static final String url="jdbc:mysql://localhost:3306/sakila_dwh?characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT";
    private static final String password="123456";
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//通过反射加载驱动程序
        Connection connection = DriverManager.getConnection(url, name, password);//通过DriverManager得到连接数据库对象

        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();


        String sql1 = "SELECT COALESCE(\n" +
                "        MAX(actor_last_update)\n" +
                "        ,   '1970-01-01 00:00:00'\n" +
                "        ) AS max_dim_actor_last_update\n" +
                "        FROM   dim_actor";

        //通过执行sql1语句返回结果集
        ResultSet rs = statement1.executeQuery(sql1);

    }
}
