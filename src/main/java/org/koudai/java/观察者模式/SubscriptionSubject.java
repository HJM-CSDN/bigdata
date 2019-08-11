package org.koudai.java.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 具体被观察者
 * 微信公众号是具体主题（具体被观察者），里面存储了订阅该公众号的微信用户，
 * 并实现了抽象主题中的方法；
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/28 02:14
 */
public class SubscriptionSubject implements Subject{

    //定义一个list来存储订阅公众号的微信用户
    private List<Observer> weixinUserList = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weixinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserList) {
            observer.update(message);
        }
    }
}
