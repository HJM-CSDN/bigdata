package org.koudai.java.继承封装多态;

/**
 * @description 继承
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/22 21:57
 */
public class 继承 {

    public static void main(String[] args) {


    }

}


//父类 子类
class Phone{
    String color = "绿色";
    int size = 100;


}

class Huawei extends Phone{ //继承


}


class Car {
    Phone phone1 = new Phone(); //创建对象
    public void test(){ //方法test
        System.out.println(phone1);
    }
}