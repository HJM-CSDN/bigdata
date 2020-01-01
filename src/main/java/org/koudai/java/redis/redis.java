package org.koudai.java.redis;

import redis.clients.jedis.Jedis;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/8/30 17:32
 */
public class redis {
    public static void main(String[] args) {
        //连接本地的Redis服务
        //先通过jedis = new Jedis(host,port);连接数据库，然后通过auth认证（输入密码）。
        //就是说分两步走，先建立连接，然后输入密码。
        Jedis jedis = new Jedis("10.169.10.39");
       // jedis.auth("unicom");
        System.out.println("连接成功");

        jedis.set("name","hjm");
        String name = jedis.get("name");
        System.out.println(name);

        boolean a = jedis.exists("name");
        System.out.println(a);
    }
}
