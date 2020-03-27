package org.unicom.scala.chapter5.scalaclass

/**
 * scala的类
 * 注意：下述代码以class开头
 */
class ScalaClass {
  /*----------------------属性字段--------------------------------*/
  private var value = 0 //初始化私有成员字段value
  
  //increment ['ɪŋkrɪm(ə)nt] 意思：增加
  def increment(){value += 1}//方法默认是公有的
  //定义一个方法返回成员变量的值
  def current() = value
  
  //使用详见Demo.scala
  
  /*--------------------setter和getter方法-------------------*/
  /*
   * scala对每个字段都默认提供getter和setter方法（如果属性可以有getter和setter方法的话）
   * getter方法和setter的权限和字段的权限一致
   * 即：
   * 1.字段权限是公有的其setter和getter方法也是共有的
   * 2.字段权限是私有的其setter和getter方法也是私有的
   * 
   * 如果字段是val，根据scala中val的含义可知，此字段只有getter方法
   * 示例如下：
   */
  //定义共有属性
  var age = 18
  //使用详见Demo.scala
  
  /*--------------------重新定义getter和setter方法-------------------*/
  private var privateName = "张三" //定义私有属性privateName
  
  //重定义getter方法
  def name = privateName
  //重定义setter方法 注意：name后面是  _= 这个符号
  def name_=(newValue: String){
    //新名字与老名字不同时才能修改名字
    if(!newValue.equals(privateName))  privateName = newValue
  }
  
  /*--------------------只带getter的属性-------------------*/
  /*
   * 有以下两种情况可以实现只读属性
   * 1.对象的属性在构造完成后就不需要更改的（即常量），可以使用val关键字
   * 2.对象的属性在构造完成之后还需要更改的，这时就没法使用val关键字了
   *   实现方式：将属性的权限设置为私有，重写一个公有的getter方法即可
   */
  
  /*--------------------只带setter的属性-------------------*/
  //在scala中无法实现只写属性
  
  /*--------------------类私有字段-------------------*/
  /*
   * 在scala中相比java有更加严格的权限限制
   * 示例如下：
   */
  private [this] var objPrivateValue = 0 
  //同是这个类的对象，对象a无法访问到对象b上的这个value属性
  //这在某些面向对象的编程语言例如：SmallTalk上很常见
  //这种类型的属性称之为对象的私有属性，scala根本不会为其生成getter和setter方法
  
  /*-----------------------指定类访问权限--------------------*/
  /*
   * scala允许将访问权限赋予指定的类。形式：private[类名]可以定义仅有
   * 指定类的方法可以访问给定的字段。这里的类名必须是当前定义的类，或者
   * 是包含该类的外部类。
   */
  
  /*-----------------------Bean属性---------------------*/
  /*
   * scala对属性生成的getter和setter方法与我们在java中常见的定义形式不同，
   * 如果程序需要与java框架结合使用，可以使用在scala中的字段上添加@BeanProperty
   * 的注解来自动生成java风格的getter和setter方法（同时也会生成scala风格的getter
   * 和setter方法，即会同时生成两套getter、setter方法）
   * 使用示例如下： 
   */
  //导入BeanProperty注解
  import scala.beans.BeanProperty
  @BeanProperty var a = 0 //自动生成两套getter、setter方法
  
  /*
   * 注意：快学scala这本书中写到@BeanProperty是在scala.reflect包中，
   * 实际现在这个版本的scala中导入时是在bean包中，有关此注解的详细信息，
   * 详情查看源码
   */
  
}