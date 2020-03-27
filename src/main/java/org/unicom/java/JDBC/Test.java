package org.unicom.java.JDBC;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2020/1/2 00:23
 */
public class Test {
    public static void main(String[] args) {
        String staff_id = "1";
        String first_name = "1";
        String last_name = "1";
        String address_id = "1";
        String picture = "1";
        String email = "1";
        String store_id = "1";
        String active = "1";
        String username = "1";
        String password ="1";
        String last_update ="1";

        String sql3 = "insert into sakila_dwh.dim_staff " +
                "(actor_last_update,staff_first_name,staff_last_name,staff_id,staff_store_id,staff_version_number,staff_valid_number,staff_active) " +
                "values ('"+last_update+"',\""+first_name+"\",\""+last_name+"\","+staff_id+")";
        System.out.println(sql3);
    }
}
