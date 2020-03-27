package edu.beicai.scala.chapter7.packgeandimport

import scala.collection.mutable.ArrayBuffer
 
/**
 * scala包
 */

/*
 * scala中的包和java中的包目的相同：管理大型程序中的名称。
 * 例如：我们之前就学习过的scala.collection.immutable.Map
 * 和scala.collection.mutable.Map就是两个不同的Map。
 * 示例如下：
 */
//在包edu.beicai中添加类a
package edu{
  package beicai{
    class a{
      
    }
  }
}
//经过上述定义，我们就可以在任何位置以edu.beicai.a的形式来访问a这类了

/*
 * 上述包定义代码不光可以写在当前源文件中，其他源文件中也可以写。
 * 即：在不同源文件中包相同是可以的。
 * 例如我们在另一个源文件中这样定义：
 */
//这里为了演示方便放在一起了，实际上应该在另一个源文件中。即b类和a类都在同一个包中。
//在包edu.beicai中添加类b
package edu{
  package beicai{
    class b{
      
    }
  }
}

/*
 * 同样的，我们可以在一个源文件中对多个包添加内容。
 * 示例如下：
 */
//在包edu.beicai1中添加类b
package edu{
  package beicai1{
    class b{
      
    }
  }
}

/*--------------------包的作用域规则--------------------*/
/*
 * scala中包同其他作用域一样支持嵌套，你可以直接访问上层包中的内容。
 * 示例如下： 
 */
//com包内含有两个父子级别的包beicai1和beicai2
package com{
  package beicai1{
    object Utils {
      def methoda()={}
    }
    package beicai2{
      class test{
        //在这里调用父包中的内容
        Utils.methoda()
      }
    }
  }
}

//假如自定义包和其他包中的内容有冲突将无法通过编译
package edu{
  package collection{
    object tt{      
    	//在这里访问collection就将无法通过编译
      /*
       * 报错信息如下：
       * object Mutable is not a member of package edu.beicai.scala.chapter7.packgeandimport.edu.collection
       */
//    	val myval = new collection.Mutable.ArrayBuffer(10)
    }
  }
}

/*--------------------串联包--------------------*/
package edu.beicai.class1506b{
  //包edu和edu.beicai中的其他成员在这里不可见
  /*
   * 因为scala中包名是相对的（可以不用像java那样使用绝对路径），
   * 所以有时候可能会造成不知道导入哪一个包中的类，通过串联包的形式可以
   * 直接指定到可访问的域，避免一个类同时出现在多个包中。
   */
}

/*--------------------文件顶部标记法--------------------*/
/*
 * 就是我们平常最常见的形式，在本源文件的头部就定义了本源文件所属的包。 
 */

/*--------------------包对象--------------------*/
/*
 * 在java中，包内部可以包含类和接口，但是不能包含函数或变量的定义，这是jvm的局限。
 * 有时候，把工具函数或常量添加到包而不是某个Utils对象，是更合理的做法。scala包对象的目的就是解决这个局限。
 * 每个包都可以有一个包对象，你需要在父包中定义，且要求名字与包名相同。
 * 示例如下：
 */
package test{
  //包对象
  package object pa{
    //定义包内部的类或对象所需使用的常量
    val one = "壹"
    //还可以定义一些Util函数
  }
  //包
  package pa{
    //包中的单例对象
    object t{
       //使用包对象中的变量
       val yi = one 
       //因为本单例对象所在的包跟包对象同处一个包（test）内，所以上述方式可行，
       //如果其他地方也想使用one这个变量，需要使用下面的方式：
       val yi1 = test.pa.one
     }
  }  
}

/*
 * 在java层面，包对象的本质是带有静态方法和静态字段的JVM类。
 * 推荐将包对象代码放在其所属的位置的源文件中（因为scala太灵活，放哪定义都可以），这样方便查找。 
 */

/*--------------------包可见性--------------------*/
/*
 * 在java中没有显示声明权限的类成员在包含该类的包中可见。
 * scala中可以添加如下形式的修饰符来达到同样的效果。
 * 示例如下：
 */
package myp{
  package sunmyp{
    class Person{
      //定义functiona函数只对sunmyp包（同包）中的其他成员可见
      private[sunmyp] def functiona = {}
      
      //定义functionb函数只对myp包（父包）中的其他成员可见
      private[myp] def functionb = {}
    }  
  }  
}

/*--------------------包引入--------------------*/
/*
 * 引入某个包以后，你想使用包内的内容就不必再写包名了，这是包引入的唯一目的。
 * 我们一开始接触导包的时候说到过导入某个包下的所有内容的方法:
 * 示例如下:
 */
//引入awt包中的所有内容
import java.awt._//与java的唯一不同在于 _ 替换了 *

//引入Color类中的所有内容
import java.awt.Color._

/*
 * scala在任何地方都可以声明引入语句，这是一个非常好的特性，
 * 可以大幅减少可能存在的命名冲突
 */

//引入包中的部分成员
import java.awt.{Color,Font}//只引入awt包中的Color和Font类，java中需要写两行来实现同样的效果

//在引入包时对引入的成员同时进行重命名
import java.util.{HashMap => JavaHashMap}
import scala.collection.mutable._
//通过上述两个引入之后，后续的代码中HashMap就指得是scala.collection.mutable.HashMap
//想直接使用java的HashMap只需直接用JavaHashMap即可

//导入包内成员时指定   不导入   某个包成员
//引入java.util包中除了HashMap之外的其他包成员
import java.util.{HashMap =>_} //"HashMap =>_"含义是将HashMap在导入时排除掉

/*
 * 就像java程序总是隐式默认引入java.lang包
 * 
 * scala的默认包引入顺序如下：
 * import java.lang._
 * import java.scala._
 * import java.Predef._//这个包中也有大量有用的函数
 * 
 * 与java有所不同的是，scala包中自己丰富过的类会将java中的覆盖掉而不引发冲突。
 * 例如scala.StringBuilder 会覆盖  java.lang.StringBuilder
 */

//因为scala包已经被默认引入了，所以往后我们可以使用如下的形式导入包
import collection.mutable.HashMap
//其与下述的引入方式只是相对地址和绝对地址的区别
import scala.collection.mutable.HashMap
