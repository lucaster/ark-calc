package ark

import scala.io.Source
import scala.util.Random
import TrapType._
import TrapAlign._

object ArkApp extends App {

  val trapNum = 7;
  val minArk = 5000;
  val waitFactor = 100000;

  val combos =
    Util.powerset(Trap.values)
      .filter { _.size == trapNum }
      .flatMap { _.toSeq.permutations }
      .map { _ map { trap => Hit(trap) } }
      .map { Combo(_) }
      .filter { _.isFeasible }
      .filter { _.ark >= minArk }
      .take(waitFactor)
      .toSeq.sortBy { -_.ark }
      .take(5)

  println(combos.size)

  combos.toList.foreach { combo => println(s"${combo.ark} ${combo.mkString}") }

  //println(combos.size)

  def a = Source.fromInputStream(getClass.getResourceAsStream("/TrapList.csv"))
    .getLines
    .drop(1)
    .map { line => line.split('|') }
    .filter { _.length > 6 }
    .map { array =>
      {
        val name = array(0).trim
        val `type` = array(1).trim
        val align = array(2).trim
        val damage = array(3).trim
        val multiplier = array(4).trim
        val points = array(6).trim
        (name, TrapType.parse(`type`), TrapAlign.parse(align), damage, multiplier, points)
      }
    }
    .filter {
      _ match {
        case (name, Some(trapType), Some(trapAlign), damage, multiplier, points) => true
        case _ => false
      }
    }
    .map { tuple =>
      {
        val name = tuple._1.replace(" ", "").replace("'", "").replace("-", "")
        val `type` = tuple._2.get
        val align = tuple._3.get
        val damage = tuple._4
        val multiplier = tuple._5
        val points = tuple._6
        s"case object $name extends TrapSkeleton($damage, $multiplier, ${`type`}, $align, $points)"
      }
    }
    .toSeq.sorted
    .foreach { println }
}