package org.unicom.scala.chapter8.extend

/**
 * scala继承
 */
object extend {
  
}

/*
 * scala扩展类的方法和java一样 — — 使用extends关键字  
 * extends是extend这个单词的第三人称单数形式。这个词的本意是扩展，
 * 经常在java领域被称之为继承（面向对象的三大特征之一），实际上称之为扩展更为合适。
 * 因此，我们在之前的学习中也见到了，scala扩展一个类也好一个特征也好都是用的是extends关键字。
 * 示例如下：
 */
//定义类
class P { }
//使用类来扩展单例对象
object test extends P{
  //得到的结果是包括类的所有特性的一个对象
}

/*
 * 与java相同，
 * 1.在类上声明final 此类就不能被扩展
 * 2.在方法上声明final 方法就不能被重写
 * 3.属性上没必要声明final，因为scala中有val
 */

/*-------------------------------重写方法--------------------------------*/
/*
 * 在scala中重写方法必须使用override修饰符
 */
class A{
  //重写toString方法（因为toString不需要参数和返回值所以可以写的很简单）
  override def toString = "xxx"
  //重写toString方法时调用父类的方法
//  override def toString = super.toString() + "xxx"
}

/*-------------------------------类型检查和转换--------------------------------*/
/*
 * scala中，类型检查关键词与java中类似。
 * 示例如下：
 */
object B{
  def main(args: Array[String]): Unit = {
    //定义变量a
    var a = 1
    /*
     * 检测变量a的类型是否是Int或其子类
     * 1.a如果为null 检测返回false
     * 2.a如果为其他类型返回false scala2.12.6
     * 3.a如果是Int或其子类都将返回true
     */
    if(a.isInstanceOf[Int]){
      //如果变量a的类型是Int将其转换成字符串
      a.asInstanceOf[Int] //执行完代码后a的类型被更新为String
    }
    
    //检测a是否是某一个类型  且不是  这个类型的子类
    if(a.getClass == classOf[Int]){
      //……
    }
    
    //使用模式匹配比类型检查更加方便，后续讲解匹配模式
     
  }
}

/*-------------------------------受保护字段和方法--------------------------------*/
/*
 * 1.和java相同，在scala中的权限控制也可以达到字段或方法级别。
 * 2.与java不同的是，protected的成员对于类所属的包而言，是不可见的。
 *   根据我们之前学过的可以在成员上直接定义所属的可见包来解决。
 * 3.scala还提供了一个protected[this]的变体，将访问权 限定在当前的对象，类似于以前学过的private[this]
 */


/*-------------------------------超类（父类）的构造--------------------------------*/
/*
 * 我们之前学习过一个类可以有一个主构造器和任意数量的辅助构造器，
 * 而且每个辅助构造器都必须对先前定义的辅构造器或本类的主构造器的调用开始。
 * 这样带来的后果是辅助构造器永远都不可能直接调用父类的构造器。
 * 子类的辅构造器最终都会调用主构造器，因为只有主构造器可以调用超类的构造器。
 * 示例如下：
 */
//定义父类（Person类）（构造器和类定义交织在一起）
class Person(name:String,age: Int){
  println("My name is: "+name+" and my age is： "+age)
}
//定义子类（雇员类）继承父类（人类）且同时定义子类的构造器，雇员类中的两个参数传递给了父类
class Employee (name: String,age: Int,val salary:Double) extends Person(name,age){  
  println("And my salary is: "+salary)
}

object testDemo{
  def main(args: Array[String]): Unit = {
    val t = new Employee("张三",18,100.0)
  }
}

/*-------------------------------重写字段--------------------------------*/
/*
 * 在scala中，我们可以在子类中重写父类中的字段（属性）
 * 示例如下：
 */
//创建Student类，带一个类参
class Student(val name: String){
  //重写toString方法
  override def toString = "My name is: "+name
}

//创建秘密学生类继承自学生类
class SecretStudent(ID: String) extends Student(ID){
  override val name = "Secret" //不暴露名字
  override val toString = "Secret" //不暴露类名
}

//上述示例不太符合实际应用，更常见的是用val重写不带参数的def，例如重写toString方法

/*
 * 重写的限制如下：
 * def只能重写另一个def（这跟java没有区别）
 * val只能重写另一个val 或不带参数的def（就是给字段属性换个值，或者重写某些不带参数的方法例如toString）
 * var只能重写另一个抽象的var（参见后面的抽象类章节） 
 */


/*-------------------------------匿名子类--------------------------------*/
/*
 * 和java相同，你可通过包含带有定义或者重写的代码块的方式创建一个匿名的子类
 * 示例如下：
 */
//与java类似
object my{
  val aa = new Student("张三"){
    def show = "xxxxxxxxxxxxxxxxxxxxxxxx"
  }
  
  //我们在其他地方使用这个类型作为参数类型的定义
  def bb ( stu: Student{def show: String}){
    
  }
}

/*-------------------------------抽象类和抽象字段--------------------------------*/
/*
 * 与java类似，scala中也可以定义抽象类，用来存放一些不需要父类实现的方法。
 * 示例如下：
 */
//定义一个抽象类，同样使用abstract关键字
abstract class myPerson{
  //定义一个抽象方法
  def methoda: Int
  
  //子类实现父类的抽象方法时无需使用override关键字。
  
  //定义一个val类型的抽象字段
  val id: Int //这是一个带有抽象的getter方法的抽象字段（因为其类型是val）
  //定义一个var类型的抽象字段
  var id2: Int//这是一个带有抽象的getter方法和抽象的setter方法的抽象字段
  
  /*
   * 抽象的字段由于在java中没有这样的概念，所以scala的代码被最终翻译成java代码时，
   * 只会保留抽象字段的抽象方法，字段本身不保留。
   */  
}

//子类实现抽象父类的时候必须提供具体的字段，例如：
class myEmployee(val id: Int) extends myPerson{//子类具有具体的id属性
  var id2 = 111 //和具体的id2属性
  
  //实现父类中的抽象方法（不需要override）
  def methoda() = 100
  
  /*
   * 我们也可以用匿名类型来定制抽象字段
   * 这跟java中通过runable接口来实现一个线程一致。
   */
  val fred = new myPerson{
    val id = 111 //定义抽象字段id1
    var id2 = 111//定义抽象字段id2
    def methoda = 234 //实现抽象方法methoda
  }
}

/*-------------------------------scala继承层级--------------------------------*/
/*
 * scala最顶层是Any类，其继承自Nothing特质（同时Nothing特质是所有类型的子类型）。
 * 类似于jvm中的Object类（引用类型）的是AnyRef，其继承自Any并实现了Null特质，其下是所有的scala class 和 java class。
 * 除了AnyRef之外Any下的一级子类还有一个 Anyval特质，其下是所有scala包装过的基本类型和Unit。
 * 所有scala类都实现ScalaObject这个特质，这个特质没有定义任何方法。
 * 
 * 详见scalaExtend.png图片（在本包中）
 * 
 * Null类型唯一的实例是null值。只能赋值给引用类型，不能赋值给基本类型的包装类型比如Int，
 * 这比java中要更加严谨一些，java中的Integer包装类是可以赋值为Null的。
 * 
 * {
 * 关于类型的值的数量：
 * 在java中，true和false是boolean类型的仅有的两个值，int类型的值数量很多，long类型的值得数量更多。
 * 所有引用类型的对象的数量都是任意多个！！！
 * } 
 * 
 * Nothing类型没有实例。对于泛型结构更加有用。
 * 举例来说，空列表Nil的类型是List[Nothing]，它是List[T]的子类型，T可以是任何类。
 * 需要注意的是，Nothing对应的不是java中的void。
 * 我们之前也学习过，在scala中，void由Unit类型表示，该类型只有一个值，那就是()。
 * 
 * 注意Unit并不是任何其他类型的超类型。但是，编译器依然允许任何值被替换成()。
 * 示例：
 * //定义函数
 * def printAny(x: Any){print(x)} 
 * def printUnit(x: Unit) {println(x)}
 * //调用函数
 * printAny("Hello") //将打印 Hello
 * printUnit("Hello")//将"Hello"替换成(),然后调用printUnit(()),打印出()
 *   
 * 和java一样，快学scala的作者建议我们远离wait、notify、synchronize这些关键字。
 * 除非我们有充分的理由使用这些关键字而不是更高层次的并发结构。（换言之，scala的作者认为这些关键字并不高效）
 */

/*-------------------------------对象相等性--------------------------------*/
/*
 * 在scala中，AnyRef的eq方法检查两个引用是否指向同一个对象。AnyRref的equals方法调用eq方法来实现效果。 
 * 如果我们创建了一个类，如果其有是否相同的比较，我们应该考虑重写equals方法。
 * 例如：我们定义一个学生类（有姓名和学号两个属性），我们就认为姓名和学号相同的学生是同一个学生
 */

class myStudent(var name:String,var stuNo:Int){
    /**
     * 将方法定义成final是因为通常而言在子类中正确的扩展相等性判断非常困难。
     * 注意方法的参数类型一定要是Any,因为重写的要求是子类中的方法和父类中的
     * 方法上的方法名和参数类型一定要保持一致。 
     */
    final override def equals(stu: Any) = {
      //将接收到的对象的类型转换成学生
      val that = stu.asInstanceOf[myStudent]
      //如果转换不成学生
      if(that == null) false
      else name.equals(that.name) && stuNo == that.stuNo
    }
}

/**
 * 测试两个学生是否重复
 */
object testStuEq{
  def main(args: Array[String]): Unit = {
    var stu1 = new myStudent("张三",15)
    var stu2 = new myStudent("张三",15)
//    println(stu1.equals(stu2))
    println(stu1 == stu2)
  }
}

/**
 * 在scala应用程序中，我们平常并不直接调用eq或equals方法，
 * 只需要使用 == 这个操作符号就好，对于引用类型而言，它会在
 * 做完必要的null检查后调用equals方法。
 */