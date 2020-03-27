package edu.beicai.scala.chapter7.packgeandimport

object A {
  
}

package random {
  
  package object random {
    var seed: Int = _
    val a = BigDecimal(1664525)
    val b = BigDecimal(1013904223)
    val n = 32

    def nextInt(): Int = {
      val temp = (seed * a + b) % BigDecimal(2).pow(n)
      seed = temp.toInt
      seed
    }

    def nextDouble(): Double = {
      val temp = (seed * a + b) % BigDecimal(2).pow(n)
      seed = temp.toInt
      temp.toDouble
    }
  }
}

package test {
  import random.random

  object Test extends App {
    random.seed = 4
    println(random.nextDouble())
    println(random.nextDouble())
    println(random.nextDouble())
    println(random.nextDouble())
    println(random.nextInt())
    println(random.nextInt())
    println(random.nextInt())
    println(random.nextInt())
  }

}
