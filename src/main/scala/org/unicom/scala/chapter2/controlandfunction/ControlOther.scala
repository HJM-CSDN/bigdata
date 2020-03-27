package org.unicom.scala.chapter2.controlandfunction

//导入math包
import math._

/**
  * 条件控制语句中的其他部分
  * 1.语句终止
  * 2.块表达式和赋值
  * 3.输入和输出
  */
object ControlOther {
    //定义main函数
    def main(args: Array[String]): Unit = {
      /*----------------------------------语句终止----------------------------------*/
      //见下面的几个示例

      //定义变量
      var n = 0
      var r = 0
      //示例1
      if (n>0){ r = r*n; n-=1}//if条件满足后要执行两条语句,可以写在一行,中间用分号分隔开就好

      //示例2
      n = n + (r + n)*n +
      23*(n)//一条语句也可以写成两行,使用操作符连接起来即可

      //示例3
      //在实际开发中,我们经常写成如下形式(花括号也可以不写)
      if (n>0){
        r = r*n
        n -= 1
      }
      /*-------------------------------表达式和赋值----------------------------------*/
      /*
       * 在java中，块语句是一个包含于{}中的语句序列。每当你需要在逻辑分支或循环中放置多个动作时，
       * 你都可以使用块语句。
       * 在scala中，{}块包含一系列表达式，其结果也是一个表达式。块中最后一个表达式的值就是块的值
       *
       */

      //定义变量
      val x = 5  //x坐标的值
      val x0 = 0 //x原点坐标的值
      val y = 4  //y坐标的值
      val y0 = 0 //y原点坐标的值

      //下属表达式表示在二维坐标中计算点(x,y)到原点(x0,y0)的距离
      val distance = {val dx = x - x0; val dy = y - y0; sqrt(dx * dy + dy * dy)}
      //从上述案例中可以看到，在二维坐标中计算一个点到另一个点的距离，
      //dx和dy仅作为计算所需要的中间值,而且这两者很好的隐藏在程序块中，对程序的其他部分不可见,
      //意味着，dx变量还可以在上述的花括号以外再次定义，并不冲突,所以其生效范围也是以{}为基准。
      val dx = 0

      //在scala中，赋值动作的返回值为Unit，这与java不同，在java中赋值语句的值就是被赋的那个值,详见示例

      //定义变量
      var a = 0
      var b = 0
      //输出赋值语句和输出变量对比
      println(a = 1)
      println(a)

      //java中下述语法是正确的，表示先将1赋值给a,然后将a=1这个表达式的值（就是1）赋值给b
      //b = a = 1

      //在scala中，下述写法是有问题的
      //b = a = 1
      //因为后面的a=1这个表达式的返回值是Unit，前面的b=a 相当于 b=Unit,这样写是错误的

      //如下方式表示，先给a赋值为1（赋值表达式的返回值Unit我们不关心也不用），然后将a的值赋给b
      a = 1
      b = a
      //所以在scala中谨记：赋值语句不要连在一起写

      /*---------------------------------输入和输出----------------------------------*/
      /*
       * 输出值使用print 或println函数
       * 此外scala还提供了一个带有C语言风格的printf函数
       * 用法如下（从s%这个变量开始，跟后面的变量的类型和位置保持一致）：
       */
      printf("Hello,%s!You are %d years old.\n","小千",22)
      //    printf("Hellow ,%s!You are %d years old.\n","Fred","j",42)//在此例中%d按照顺序会匹配"j"，类型不匹配，运行时将会报错

      /*
       * 输入使用readLine函数从控制台读取一行输入。
       * 其他与其类似的函数有：
       * readInt			读取Int类型的值
       * readDouble		读取Double类型的值
       * readByte			读取Byte类型的值
       * readShort		读取Short类型的值
       * readLong			读取Long类型的值
       * readFloat		读取float类型的值
       * readBoolean	读取Boolean类型的值
       * readChar			读取Char类型的值
       * 注意：上述所有函数中，只有readLine函数可以添加参数，其他函数参数必须为空
       * 示例如下：
       */

      //上述函数已经过时了，在scala2.11.0版本中，由scala.io.StdIn.readLine()替代，详见源码
      //旧版API写法
      //    val name = readLine("Your name")
      //    print("Your age: ")
      //    val age = readInt()
      //    printf("Hello ,%s!Next year,you will be %d.\n",name,age+1)

/*      //新版API写法
      val nameNew = scala.io.StdIn.readLine("Your name:")
      print("Your age:")
      val ageNew = scala.io.StdIn.readInt()
      printf("Hello,%s!Next year, you will be %d.\n",nameNew,ageNew+1)

      //新版其他函数用法(剩余课上讲解)
      println("Place input a Double value:")
      val double = scala.io.StdIn.readDouble()
      println("The Double value you input is: "+double)*/
    }

}
