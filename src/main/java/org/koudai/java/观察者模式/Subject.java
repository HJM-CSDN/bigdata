package org.koudai.java.观察者模式;
//抽象被观察者
public interface Subject {
    //抽象主题，提供了attach、detach、notify三个方法

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(Observer observer);

    /**
     * 通知订阅者更新消息
     * @param message
     */
    public void notify(String message);

}
