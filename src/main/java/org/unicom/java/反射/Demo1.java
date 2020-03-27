package org.unicom.java.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2020/3/12 21:11
 */
public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> women = Class.forName("org.unicom.java.OOP.Women");

        Method method [] = women.getDeclaredMethods();

        for (int i = 0; i < method.length; i++) {
          //  System.out.print(method[i].getModifiers()+" ");
            /**
             * 取得修饰符,程序中找的不是关键字，而是关键字的代表数字，可以借助于Modifier类
             */
            System.out.print(Modifier.toString(method[i].getModifiers())+" ");
            /**
             * 取得方法的返回参数类型
             */
            System.out.print(method[i].getReturnType().getSimpleName()+" ");

            /**
             * 取得类名称
             */
            System.out.print(method[i].getName()+"(");

            /**
             * 取得全部参数
             */
            Class<?> params [] = method[i].getParameterTypes();

            //有参数就输出
            if (params.length > 0){
                for (int i1 = 0; i1 < params.length; i1++) {
                    System.out.print(params[i1].getSimpleName() + " arg-" + i1);
                    if (i1 < params.length-1){//还有参数就拼接逗号
                        System.out.print(",");
                    }
                }
            }
            System.out.print(")");
            /**
             * 取得所有抛出的异常
             */
            Class<?> exps [] = method[i].getExceptionTypes();
            if (exps.length>0){
                System.out.print(" throws");
                for (int i1 = 0; i1 < exps.length; i1++) {
                    System.out.print(exps[i1].getSimpleName());
                    if (i1<exps.length-1){
                        System.out.print(",");
                    }
                }
            }

            System.out.println();
        }



    //    Object obj = women.newInstance();//等价于实例化对象
        /**
         * 如果使用反射实例化类对象，必须要求类中有无参构造方法，因为默认使用Class类的
         * newInstance（）方法只能够找到无参构造方法实例化对象
         */
       // System.out.println(obj);
       // Object obj2 = women.getConstructor();

        Constructor<?> cons1 = women.getConstructor(String.class,Integer.class);
        Object object = cons1.newInstance("fang",11);

        System.out.println(object);



        Class<?> cls = Class.forName("java.lang.String");
        Constructor<?> [] cons = cls.getConstructors();
        for (int i = 0; i < cons.length; i++) {
       //     System.out.println(cons[i]);
        }

    }


}
