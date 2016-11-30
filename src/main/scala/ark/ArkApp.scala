package ark

object ArkApp extends App {

  import scala.io._
  import java.io._

  val trapNum = 8;
  val ark = 10000;
  
  val combos = Util.powersetStream(Trap.values)
    .filter { _.size <= trapNum }
    .map { traps => Combo(traps.toList map { Hit(_) }) }
    .filter { _.ark >= ark }
    .take(1)
    //.toList
  //.toList.par
  //.map { traps => Combo(traps map { Hit(_) }) }

  combos.foreach { combo => println(s"${combo.ark} ${combo.mkString}") }

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