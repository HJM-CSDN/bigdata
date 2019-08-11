package org.koudai.java.观察者模式;

/**
 * @description 客户端调用
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/28 02:19
 */
public class Client {
    public static void main(String[] args) {
        //创建一个公众号
        SubscriptionSubject mSubscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WeixinUser user1 = new WeixinUser("韩金铭");
        WeixinUser user2 = new WeixinUser("小千");
        WeixinUser user3 = new WeixinUser("小芳");

        //订阅公众号(公众号增加订阅者）
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);

        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("中国建国70周年纪念日要来了！");
    }
}
