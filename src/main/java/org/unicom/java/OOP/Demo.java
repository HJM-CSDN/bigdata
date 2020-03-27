package org.unicom.java.OOP;

/**
 * @description 面向对象 构造方法
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/10/6 21:24
 */
public class Demo {
    public static void main(String[] args) {
        BigDog doudou = new BigDog();//调用无参构造方法创建一个对象
        BigDog fang = new BigDog("芳",10);//调用有参构造方法创建一个对象
        System.out.println(doudou.name);
        System.out.println(fang.name);
        System.out.println(fang.age);
        String a = fang.eat();

        System.out.println(a);
    }
}
class BigDog{
    String name; //成员变量
    int age;
   public BigDog(){
        System.out.println("默认构造方法");
    }

    public BigDog(String name,int age){ //局部变量
        this.name = name;
        this.age = age;
        System.out.println("调用有参构造方法");
    }

    public void bark(){//定义方法 无返回值 void 空类型返回值
        System.out.println("汪 汪 ！");
    }

    public String eat(){//带有返回值的方法
       return "屎";
    }

}