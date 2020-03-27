package org.unicom.java.anonymous_object;

/**
 * @description 匿名对象
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2018/11/26 08:32
 */
public class AnonymousObject {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.name = "金毛";

        new Dog().name = "柯基";

        System.out.println(dog.name);
        System.out.println(new Dog().name);//匿名对象只在创建时有效,用完即释放,此处创建的新对象name默认为null
    }
}

class Dog{
    String name;
}