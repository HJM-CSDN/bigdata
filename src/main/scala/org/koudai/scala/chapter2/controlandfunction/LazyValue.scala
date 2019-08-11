package org.koudai.scala.chapter2.controlandfunction

/**
  * scala懒值
  */
object LazyValue {
  /*
    定义main函数(过程)
   */
  def main(args: Array[String]): Unit = {
    /*
     * 当val被声明为lazy时，他的初始化将被推迟，直到我们首次对他取值。
     * 示例如下：
     */
    //读取一个文件中的所有字符并拼接成一个字符串（后续详细讲解）
    //这里需要注意的是，文件不要在C盘中创建容易造成权限问题
    lazy val words = scala.io.Source.fromFile("F:\\scala\\scala\\chapter2\\controlandfunction\\readme").mkString
    println(words)

    //验证方式，将readme改成一个名字不存在的文件，当执行到println("xxxxxxxxxxx")时，
    //程序不会报找不到文件的错，就说明程序执行时没有执行文件读取操作，如下所示：
        lazy val words2 = scala.io.Source.fromFile("F:\\scala\\scala\\chapter2\\controlandfunction\\readme2").mkString
        println("xxxxxxxxxxx")
        println("yyyyyyyyyyy")
    //    println(words2)  //此处才开始抛出异常,说明懒值用到时在执行初始化语句

    /*
     * 懒值对于开销较大的初始化语句而言十分有用。
     * 我们可以将懒值当做是介于val和def的中间状态。
     * 示例如下:
     */
    //在words1被定义时（即代码执行到下面的这一行时）即被取值
    val words1 = scala.io.Source.fromFile("F:\\scala\\scala\\chapter2\\controlandfunction\\readme").mkString
    //在words被首次使用时取值
    lazy val words4 = scala.io.Source.fromFile("F:\\scala\\scala\\chapter2\\controlandfunction\\readme").mkString
    //在每一次words3（函数）被使用时取值
    def words3 = scala.io.Source.fromFile("F:\\scala\\scala\\chapter2\\controlandfunction\\readme").mkString
    /*
     * 说明：
     * 懒值也有额外的开销。我们每次访问懒值，都会有一个方法被调用。
     * 而这个方法将会以线程安全的方式检查该值是否已被初始化。
     */
  }
}
