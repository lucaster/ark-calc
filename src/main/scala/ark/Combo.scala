package ark

import scala.math.BigDecimal.int2bigDecimal

import ark.Trap._
import ark.TrapType._
import ark.TrapAlign._
import ark.TrapEffect._

case class Combo(val hits: Seq[Hit] = Nil) {

  /**
   * Current total ark
   */
  def ark = Combo.ark(hits).toInt

  def elaborate = Combo.elaborate(hits).toInt
  def humiliating = Combo.humiliating(hits).toInt
  def sadistic = Combo.sadistic(hits).toInt

  /**
   * Combo ark if the hit were added.
   */
  def ark(candidate: Hit) = Combo.ark(hits :+ candidate).toInt;

  /**
   * Appends the hit at the end of the Combo.
   *
   * @return a new Combo instance
   */
  def append(hit: Hit) = copy(hits :+ hit)

  def isFeasible = Combo.isFeasible(this);

  def mkString = hits map { hit => s"(${hit.trap}, ${hit.bonusMultiplier})" } mkString
}

case object Combo {

  def summer(filter: Hit => Boolean, scorer: Hit => BigDecimal): Traversable[Hit] => BigDecimal = {
    hits =>
      (hits.filter { filter }.map { scorer }.sum) * (hits.map { _.multiplier }.sum)
  }

  def damageSum = summer(_ => true, _.damage)
  def elaborateSum = summer(_.trap.align == Elaborate, _.trap.points)
  def sadisticSum = summer(_.trap.align == Sadistic, _.trap.points)
  def humiliatingSum = summer(_.trap.align == Humiliating, _.trap.points)

  def totaler(summer: Traversable[Hit] => BigDecimal): Seq[Hit] => BigDecimal = {

    def theTotaler = totaler(summer)

    hits =>
      if (hits.isEmpty) {
        0
      }
      else {
        val prev = hits.take(hits.size - 1)
        val last = hits.last
        if (!prev.contains(last)) theTotaler(prev) + summer(hits) else 2 * theTotaler(prev)
      }
  }

  def ark = totaler(damageSum)
  def elaborate = totaler(elaborateSum)
  def sadistic = totaler(sadisticSum)
  def humiliating = totaler(humiliatingSum)

  def isValidSequence(trap1: Trap, trap2: Trap): Boolean = {

    if (Set[TrapType](Floor, Ceiling).contains(trap1.kind) &&
      Set[TrapType](Floor, Ceiling).contains(trap2.kind)) {
      if (!trap1.effects.contains(HitsNear) && !trap2.effects.contains(HitsNear)) {
        if (!trap1.movesVictim /* && !trap2.movesVictim*/ ) {
          // TODO: è la prima che deve muovere, la seconda anche no
          // Test case: MagnifyingGlass, Washbin non va bene infatti
          return false
        }
      }
    }

    return true;
  }

  def isFeasible(traps: Seq[Trap]): Boolean = {

    def allTrapsLegal = traps.filter { !_.isLegal }.isEmpty

    def onlyOnce = traps.distinct.size == traps.size

    // TODO: very slow
    def allPairsValid = traps
      .sliding(2)
      .collect {
        case pair => {
          !isValidSequence(pair(0), pair(1))
        }
      }
      .isEmpty

    allTrapsLegal //&& allPairsValid
  }

  def isFeasible(combo: Combo): Boolean = isFeasible(combo.hits.map { hit => hit.trap });
}