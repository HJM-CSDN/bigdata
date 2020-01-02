package org.koudai.java.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @description jdbc
 * @Author: 林圆圆 1329674322@qq.com
 * @Date: 2019/12/2 14:20
 */
public class Staff {
    private static final String name = "root";
    private static final String url="jdbc:mysql://localhost:3306/sakila_dwh?characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT";
    private static final String password="123456";
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//通过反射加载驱动程序
        Connection connection = DriverManager.getConnection(url,name,password);//通过DriverManager得到连接数据库对象

        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();


        String sql1 = "SELECT	" +
                "COALESCE(MAX(staff_last_update),'1970-01-01 00:00:00')" +
                " max_dim_staff_last_update" +
                " FROM dim_staff";

        //通过执行sql1语句返回结果集
        ResultSet rs = statement1.executeQuery(sql1);

        String resultSql1 = "";
        while(rs.next()){
            resultSql1 = rs.getString("max_dim_staff_last_update");
        }
        System.out.println("第一步sql的返回结果是："+resultSql1);

        ResultSet rs2 = statement2.executeQuery("SELECT staff_id" +
                        ",first_name" +
                        ",last_name" +
                        ",address_id" +
                        ",picture" +
                        ",email" +
                        ",store_id" +
                        ",active" +
                        ",username" +
                        ",password" +
                        ",last_update " +
                        "FROM sakila.staff WHERE last_update > '"+resultSql1+"'");

        while(rs2.next()){
            int staff_id = rs2.getInt("staff_id");
            String first_name = rs2.getString("first_name");
            String last_name = rs2.getString("last_name");
            String address_id = rs2.getString("address_id");
            String picture = rs2.getString("picture");
            String email = rs2.getString("email");
            int store_id = rs2.getInt("store_id");
            String active = rs2.getString("active");
            String username = rs2.getString("username");
            String password = rs2.getString("password");
            String last_update = rs2.getString("last_update");
            System.out.println("staff_id: "+staff_id+"first_name: "+first_name+"  last_name: "+last_name+"  address_id: "+address_id+"  picture: "+picture+"  email: "+email+"  store_id: "+store_id+"  active: "+active+"  username: "+username+"  password: "+password+"  last_update: "+last_update);

            if (active.equals("1")){
                active = "Yes";
            }else if (active.equals("0")){
                active = "No";
            }

            String sql3 = "insert into sakila_dwh.dim_staff (actor_last_update,staff_first_name,staff_last_name,staff_id,staff_store_id,staff_version_number,staff_valid_number,staff_active) values ('"+last_update+"',\""+first_name+"\",\""+last_name+"\","+staff_id+")";
//
//            System.out.println(sql3);
//            statement3.executeUpdate(sql3);

        }

        rs.close();
//        rs2.close();
        connection.close();
    }
}
