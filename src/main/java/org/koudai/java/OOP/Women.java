package org.koudai.java.OOP;

/**
 * @description 面向对象
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/27 17:00
 */
public class Women {
    String name;
    Dog dog = new Dog();
    Cat cat = new Cat();


    public void IntroducePet(){
        System.out.println("这是我家的狗狗"+dog.name+",它"+dog.age+"岁了");
        System.out.println("这是我家的猫咪"+cat.name+",它"+cat.age+"岁了");
    }

    public static void main(String[] args) {
        Women xiaofang = new Women();
        xiaofang.name = "小芳";
        xiaofang.IntroducePet();
    }
}
class Dog{
    String name = "豆豆";
    int age = 10;
}

class Cat{
    String name = "咪咪";
    int age = 8;
}