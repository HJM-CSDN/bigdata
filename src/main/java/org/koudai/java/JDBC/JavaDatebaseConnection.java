package org.koudai.java.JDBC;
import java.sql.*;

/**
 * @description jdbc
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/2 14:20
 */
public class JavaDatebaseConnection {
    private static final String name = "root";
    private static final String url="jdbc:mysql://localhost:3306/sakila_dwh?characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT";
    private static final String password="123456";
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//通过反射加载驱动程序
        Connection connection = DriverManager.getConnection(url,name,password);//通过DriverManager得到连接数据库对象

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

//        actor      dim_actor
//        ya  1          ya    1
//        ba  2
//        ca  2
//        dc  3

        String resultSql1 = "";
        while(rs.next()){
            resultSql1 = rs.getString("max_dim_actor_last_update");
        }
        System.out.println("第一步sql的返回结果是："+resultSql1);

        //ResultSet rs2 = statement.executeQuery("SELECT actor_id, first_name, last_name, last_update FROM sakila.actor WHERE last_update > to_date('"+a+"', 'yyyy-mm-dd hh24:mi:ss')");
        ResultSet rs2 = statement2.executeQuery("SELECT actor_id, first_name, last_name, last_update FROM sakila.actor WHERE last_update > '"+resultSql1+"'");



        while(rs2.next()){
            String actor_id = rs2.getString("actor_id");
            String first_name = rs2.getString("first_name");
            String last_name = rs2.getString("last_name");
            String last_update = rs2.getString("last_update");
            System.out.println("actor_id:"+actor_id+" first_name:"+first_name+" last_name:"+last_name+" last_update:"+last_update);


            String sql3 = "insert into sakila_dwh.dim_actor (actor_last_update,actor_last_name,actor_first_name,actor_id) values ('"+last_update+"',\""+last_name+"\",\""+first_name+"\","+actor_id+")";

            System.out.println(sql3);
            statement3.executeUpdate(sql3);

        }

        rs.close();
        rs2.close();
        connection.close();
    }
}
