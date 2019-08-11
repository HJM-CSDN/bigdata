package org.koudai.java.观察者模式;

/**
 * @description 观察者模式
 * 观察者模式：即发布订阅模式，它定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 当这个主题对象发生变化时，会通知所有的观察者对象，使它们能够更新自己。
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/28 02:03
 */
//抽象观察者（Observer）
public interface Observer {
    public void update(String message);
}
