package org.unicom.scala.chapter6.scalaObject

/**
 * scala中的对象 * 
 */
object ScalaObject {
  
  /*--------------------单例对象--------------------*/
  /*
   * scala中没有静态方法或静态字段，可以使用object这个语法结构来代替。
   * 实现一个单例对象即我们最常用的运行scala程序的方式。
   * 即：object ScalaObject 这种形式（我们讲课以来使用的都是这种方式）
   */
  //定义一个私有变量
  private var lastNumber = 0
  //定义一个自增函数
  def newNumber() = {lastNumber += 1; lastNumber}
  
  
  /*
   * 当你需要一个数字时，可以使用如下的形式： 
   * ScalaObject. newNumber()
   * 注意：在上述方法第一次被调用时ScalaObject这个  “对象的构造器” 被jvm自动调用
   * 如果一个对象从未被使用，则从来不会初始化。
   * 
   * scala中，object本质上可以拥有类的所有特质--除了不能
   * 提供构造器的参数（意味着：我们只能使用默认构造函数）。
   */
  
  /*
   * 对于任何你在java中使用单例的地方，在scala中都可以用object来实现：
   * 1.作为存放工具函数或常量的地方。
   * 2.高效的共享单个不可变实例。
   * 3.需要用单个实例来协调某个服务时（即：常见的单例模式）。
   * 
   * 很多人不看好单例模式。但是Scala提供的是工具，
   * 可能做出更好的设计，也可能做出糟糕的设计，你需要自己做出判断
   * （即：如果需要多例类型的对象，可以自己创建类然后自行创建多个对象）。
   */
  
  
  //定义main函数
  def main(args: Array[String]): Unit = {
    //调用当前object上的方法
    val newNum = ScalaObject.newNumber()
    //输出函数结果
    println(newNum)
  }
  
  /*--------------------伴生object--------------------*/
  /*
   * 在java中，我们通常设计的类中会包同时含实例方法
   * （需要通过对象调用的普通方法）和静态方法。
   * 在scala中，可以通过类和与类名同名的“伴生”object来实现同样的操作。
   * 即：伴生object用来保存静态方法，类中存放实例方法。
   * 类和它的object可以相互访问私有属性。它们必须存在于同一个源文件中。
   * 
   */
  
  //类Account
  class Account{
    //获取一个唯一ID
    val id = Account.newUniqueNumber();
    //获取这个ID下的账户余额（这里默认为0）
    private var balance = 0.0
    //给这个ID下的账户deposit（存款） 余额+本次存入量
    def deposit(amount: Double){balance += amount}
  }
  //类Account的伴生对象Account
  object Account{
    //定义一个私有变量
    private var lastNumber = 0
    //定义一个自增函数用来获取唯一ID
    def newUniqueNumber() = {lastNumber += 1; lastNumber}
  }
  
  /*--------------------扩展类或者特质的object--------------------*/
  /*
   * 在scala中的特质与java中的接口有点类似，但是java中接口中不能有方法实现（1.8以前），
   * scala允许特质中的方法可以有方法体。特质的示例如下：
   * 
   * trait XX{
   * 	def function(){……}
   * }
   * 
   * 同样，特质（有意见认为trait这个词应该翻译成特征更合适）与接口类似，所以可以多继承，
   * 意思就是一个object可以有很多的特征 。
   * 
   * 同样，scala中也有抽象类，跟java中抽象类与接口的区别相同，scala中的抽象类跟特质的区别在于多继承。
   *
   * 一个对象object可以扩展类以及一个或多个的特质，造成的
   * 结果是它变成了一个指定了类型以及特质的类的对象，同时
   * 拥有在对象定义中给出的所有特性。
   * 
   * 一个有用的场景是类中的某个方法参数内容可以有很多，最常用的就是特定的内容的，
   * 其他内容的参数不太常用，这时候我们就可以使用一个object来继承这个类，语法如下：
   * object xxx extends 类xxx 类参数xxx {
   * 	调用某个方法（传入特定的常用参数内容）
   * }
   * 
   * 说白了，上述所做的就是给一个类的某个方法的最常见（或者默认）参数的这种确定值的引用创建一个单例对象。
   * 具体示例详见下文。
   */
  
  /*--------------------apply方法--------------------*/
  /*
   * 通常，apply方法返回的是伴生object的对象。一般用于下述的形式：
   * Object(参数1,...,参数N)
   * 举例来说，Array object定义了apply方法，让我们可以用下面这样的表达式来创建数组：
   * Array(1,2,3)
   * 
   * 上述形式使用构造函数同样可以实现， apply方法相对于构造函数来说，
   * 尤其是对于嵌套表达式而言，可以省去new关键字。
   * 例如：Array(Array(1,1),Array(0,0))
   * 
   * 但是一定要注意下述代码的差异：
   * Array(100) 		//一个Array对象，内含一个叫“100”的元素
   * new Array(100) //一个Array对象，内含100个元素
   * 示例如下： 
   */

//  Array(1,2,3)
  
  
  //定义类
  class Accountnew private (val id:Int, initialBalance:Double){
    private var balance = initialBalance
    //其他业务代码
  }
  
  //定义类的伴生对象，并在其中实现apply方法
  object Accountnew {//伴生对象Accountnew
    //定义一个私有变量
    private var lastNumber = 0

    //定义一个自增函数用来获取唯一ID
    def newUniqueNumber() = {lastNumber += 1; lastNumber}
    
    def apply(initialBalance: Double) ={
      new Accountnew(newUniqueNumber(),initialBalance)
    }
  }
  
  //做好上述的准备工作后，我们就可以用如下代码来构造账号了：
  val accountWithBalance = Accountnew(1000.0)
  //上述创建对象的过程类似于我们在银行存钱，有初始余额，但是银行卡号是银行给我们提供的
  
}

/*--------------------应用程序对象--------------------*/
/*
 * 每个scala程序（新api里叫App）都必须从一个object的main方法开始！！！
 * 这与java不同，这也是讲课以来的所有用法！
 */
//定义一个单例对象a 这个a没有任何扩展
object a{
  def main(args: Array[String]): Unit = {
    println("程序从这里开始运行~~")
b
  }
}
//定义一个object b 继承自App特质
//同样这里跟java有些区别，java中我们称之为实现接口，使用implements关键字
//在scala中“实现”一个特质使用的是extends关键字
object b extends App {
  println("Hello,world")
}

/*
 * 上述单例对象b内之所以没有写main方法也能被运行，
 * 是因为App特质又扩展自另一个特质DelayedInit， 
 * 编译器对该特质有特殊处理。所有带有该特质的类
 * （带有某个特质，更准确说带有某个特征。其本质就是继承这个特质）
 * 其初始化方法都会被挪到delayedInit方法中。
 */

/*--------------------枚举--------------------*/
/*
 * 与java不同，scala中并没有枚举类型，不过scala提供了一个Enumeration助手类，可以帮助产出枚举。
 * 示例如下： 
 */
//定义一个交通信号灯颜色
object TrafficLightColor extends Enumeration{
  //下述所有形式的定义，Value方法及其参数都是scala固定的
  
  //最简单版定义
//  val Red, Yellow, Green = Value
  //也可以用Value对他们进行丰富初始化（传入ID和名称）
  val Red = Value(0,"Stop")//标准传入两个参数
  val Yellow = Value(10)//只传入了ID，名称默认和参数名相同
  val Green = Value("Go")//只传入了名称，ID默认从前一个枚举值上加一
}

//创建一个测试枚举的单例对象
object testEnumer{
  def main(args: Array[String]): Unit = {
    //使用枚举的形式一（不导入枚举）
    println(TrafficLightColor.Yellow)//输出枚举对象Yellow身上的值
    println(TrafficLightColor(0))//输出TrafficLightColor的ID为0的那个枚举对象的值

    //使用枚举的形式二（导入枚举）
    import TrafficLightColor._
    println(Green)//输出枚举对象Green身上的值
    println(Green.id)//输出枚举对象Green的ID
    /*
     * 需要注意的是，枚举的类型是TrafficLightColor.Value，
     * 而TrafficLightColor.Green是握有某个值的对象。
     */
  }
}