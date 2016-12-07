package ark

import scala.io.Source
import scala.util.Random
import Trap._
import TrapType._
import TrapAlign._
import TrapEffect._

object ArkApp extends App {

  val trapNum = 7
  val minArk = 5000
  val minElaborate = 1000
  val minHumiliating = 1000
  val minSadistic = 1000
  val waitFactor = 100

  val japCombo = Combo(Seq[Hit](
    Hit(Springboard),
    Hit(Washbin, 0.1),
    Hit(WallNudge, 0.1),
    Hit(PumpkinMask),
    Hit(LethalLance, 0.1),
    Hit(Vase),
    Hit(IronRake, 0.1),
    Hit(BearTrap),
    Hit(SwingingAxe, 0.1)))

  println(Combo.scores(japCombo))

  time({

    val traps = (Trap.values
      .filter { !_.explodes }
      .filter { !_.isProjectile }
      .filter { !_.rolls }
      .filter { _.movesVictim }
      .-(ChurchBell, MagnifyingGlass, Claw, InverseSaltire, ReapersScythe)
      .toSeq)

    traps.combinations(trapNum)
      .map { _ map { Hit(_) } }
      .map { Combo(_) }
      .filter { _.isFeasible }
      .filter { _.ark >= minArk }
      .filter { _.elaborate >= minElaborate }
      .filter { _.humiliating >= minHumiliating }
      .filter { _.sadistic >= minSadistic }
      //.filter { _.hits.filter { _.trap.isProjectile }.size <= 1 }
      .take(waitFactor)
      .toSeq.sortBy { -_.ark }
      .foreach { print }
  })

  def print(combo: Combo) =
    println(s"a${combo.ark} e${combo.elaborate} h${combo.humiliating} s${combo.sadistic} ${combo.mkString}")

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000 + "s")
    result
  }

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