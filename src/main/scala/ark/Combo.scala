package ark

import scala.math.BigDecimal.int2bigDecimal

import ark.TrapType._
import ark.TrapEffect._

case class Combo(val hits: List[Hit] = Nil) {

  /**
   * Current total ark
   */
  def ark = Combo.ark(hits).toInt

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

  def sum(hits: List[Hit]) = hits.map(_.damage).sum * hits.map(_.multiplier).sum

  def ark(hits: List[Hit]): BigDecimal = {
    if (hits.isEmpty) {
      0
    }
    else {
      val prev = hits.take(hits.size - 1)
      val last = hits.last
      if (!prev.contains(last)) ark(prev) + sum(hits) else 2 * ark(prev)
    }
  }

  def isValidSequence(trap1: Trap, trap2: Trap): Boolean = {

    if (!trap1.movesVictim && !trap2.movesVictim) {
      if (!trap1.effects.contains(HitsNear) && !trap2.effects.contains(HitsNear)) {
        if (trap1.kind == Floor && trap2.kind == Floor) {
          return false
        }
        if (trap1.kind == Ceiling && trap2.kind == Ceiling) {
          return false
        }
        if (trap1.kind == Floor && trap2.kind == Ceiling) {
          return false
        }
        if (trap1.kind == Ceiling && trap2.kind == Floor) {
          return false
        }
      }
    }

    return true;
  }

  def isValidSequence(trap1: Trap, trap2: Trap, trap3: Trap): Boolean = true

  def isFeasible(combo: Combo) = {

    type ComboValiation = Combo => Boolean

    val yes: ComboValiation = combo => true

    ???
  }
}