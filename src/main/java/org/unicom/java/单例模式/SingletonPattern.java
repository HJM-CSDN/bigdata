package org.unicom.java.单例模式;
/**
 * @description 单例模式
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2015/11/14 18:57
 */

/**
 * 单例设计模式:一个类只允许有一个对象,将这个对象作为一个全局的访问点提供出去供大家使用
 * 分析:
 * 1.用户只能有一个对象
 * 2.全局的访问点:得到的对象就是全局的访问点,如何做到全局??让static去修饰
 * 3.如何提供出去?
 * 4.供大家使用?--单例的功能
 */

//创建单例类
//方式一:饿汉式,在定义变量的同时完成初始化

class Singleton1{
    private static Singleton1 singleton1 = new Singleton1();
    //将构造方法私有化
    private Singleton1(){}
    //使用公共的方法将变量提供出去,并将方法设置成静态的,可以通过类名
    public static Singleton1 getInstance(){
        return singleton1;
    }
}
//方式二:懒汉式,开始只是定义变量,什么时候使用什么时候赋值
class Singleton2{
    private static Singleton2 singleton2 = null;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if (singleton2==null) {//尽量减少线程安全代码的判断次数
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
//主类
public class SingletonPattern {
    public static void main(String[] args) {
        Singleton2 singleton1 = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton1 == singleton2); //证明懒汉式只创建了一个对象
    }
}

/**
	单例模式实现过程如下：
	首先，将该类的构造函数私有化（目的是禁止其他程序创建该类的对象）；
	其次，在本类中自定义一个对象（既然禁止其他程序创建该类的对象，就要自己创建一个供程序使用，否则类就没法用，更不是单例）；
	最后，提供一个可访问类自定义对象的类成员方法（对外提供该对象的访问方式）。

	直白的讲就是，你不能用该类在其他地方创建对象，而是通过该类自身提供的方法访问类中的那个自定义对象。

	那么问题的关键来了，程序调用类中方法只有两种方式，①创建类的一个对象，用该对象去调用类中方法；②使用类名直接调用类中方法，格式“类名.方法名()”；
	上面说了，构造函数私有化后第一种情况就不能用，只能使用第二种方法。
	而使用类名直接调用类中方法，类中方法必须是静态的，而静态方法不能访问非静态成员变量，因此类自定义的实例变量也必须是静态的。
	这就是单例模式唯一实例必须设置为静态的原因
 */

/*
public class SingletonPattern {
	public static void main(String[] args) {
		Test test = new Test();
		Thread thread = new Thread(test);
		thread.start();
	}

}
//单例模式三要素:
// 1.私有的构造方法
// 2.指向自己实例的私有静态引用
// 3.以自己的实例为返回值的静态的公有的方法
// 饿汉式
class Singleton {
	private static Singleton singleton = new Singleton();// 指向自己实例的私有静态引用
	private Singleton() {
	}// 私有的构造方法
	public static Singleton getSingleton() {// 以自己实例为返回值的静态的公有的方法
		return singleton;//这里只有一行代码,不会发生线程安全问题
	}
}

// 懒汉式
class Singleton1 {
	private static Singleton1 singleton = null;
	//private  static String name = "张三";
	private Singleton1() {

	}// 私有的构造方法

	public static Singleton1 getSingleton() {
		if (singleton == null) {//目的:尽量减少线程安全代码的判断次数
			synchronized (Singleton1.class) {
				// synchronized (Object.class)
				if (singleton == null) {
					singleton = new Singleton1();
				}
			}
		}
		return singleton;
	}
}

class Test implements Runnable{
	public void run(){
		Singleton singleton = Singleton.getSingleton();

	}
}
*/