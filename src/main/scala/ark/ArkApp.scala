package ark

import java.math.BigInteger

import scala.math.BigDecimal.int2bigDecimal

import rx.lang.scala.Observable

object ArkApp extends App {

  val a = ark.enums.Trap.AcidSlime
  val v = ark.enums.Trap.values()

  time({
    val traps = Traps.values
      .filter { !_.explodes }
      //.filter { !_.isProjectile }
      //.filter { !_.rolls }
      //.filter { _.movesVictim }
      //.-(ChurchBell, Claw, MagnifyingGlass)
      .toSeq
      .sortBy(r => (-r.multiplier, r.points))
      .toArray
    val trapNum = 7
    val minArk = 5000
    val minElaborate = 3000
    val minHumiliating = 3000
    val minSadistic = 3000
    val take = 100

    val tot = on(traps.size, trapNum);
    println(s"${traps.size} traps, ${tot} combinations")
    var count = BigInteger.ZERO
    Observable.from(traps.combinations(trapNum).toIterable)
      .doOnEach { _ =>
        count = count.add(1)
        if (count.mod(1000000).equals(BigInteger.ZERO)) {
          println(s"Combo # ${count} / ${tot} (${count.multiply(100).divide(tot)}%)")
        }
      }
      .map { _ map { Hit(_) } }
      .map { Combo(_) }
      //.filter { _.isFeasible }
      .filter { _.ark >= minArk }
      .filter { _.elaborate >= minElaborate }
      .filter { _.humiliating >= minHumiliating }
      .filter { _.sadistic >= minSadistic }
      .doOnEach(print _)
      .take(take)
      .subscribe
  })

  def print(combo: Combo) =
    println(s"a${combo.ark} e${combo.elaborate} s${combo.sadistic} h${combo.humiliating} ${combo.mkString}")

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000 + "s")
    result
  }

  def on(n: BigInteger, k: BigInteger) = factorial(n).divide(factorial(k)).divide(factorial(n.subtract(k)))

  implicit def int2BigInteger(n: Int): BigInteger = BigInteger.valueOf(n)

  def factorial(n: BigInteger): BigInteger = {
    if (BigInteger.ZERO.equals(n))
      BigInteger.ONE
    else
      n.multiply(factorial(n.subtract(BigInteger.ONE)))
  }
}