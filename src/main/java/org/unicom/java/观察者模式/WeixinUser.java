package org.unicom.java.观察者模式;

/**
 * @description 具体观察者，微信用户为例
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/28 02:07
 */
public class WeixinUser implements Observer{
    private String name;
    public WeixinUser(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
