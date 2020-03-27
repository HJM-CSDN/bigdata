package org.unicom.java.OOP;

import java.io.IOException;

/**
 * @description 面向对象
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/27 17:00
 */
public class Women {
    private String name;
    private Integer age;
    Dog dog = new Dog();
    Cat cat = new Cat();

    public Women(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public void print(){
        System.out.println("我会画画");
    }

    public void IntroducePet(String name,Integer age)throws IOException{

        System.out.println("这是我"+name+","+age+"岁了");
        System.out.println("这是我家的狗狗"+dog.name+",它"+dog.age+"岁了");
        System.out.println("这是我家的猫咪"+cat.name+",它"+cat.age+"岁了");
    }

    @Override
    public String toString(){
        return "name是"+name+",age是"+this.age;
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