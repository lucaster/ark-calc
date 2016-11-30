package ark

import scala.annotation.tailrec

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

  def mkString = hits map { hit => s"(${hit.trap}, ${hit.bonusMultiplier})" } mkString
}
case object Combo {
  def sum(hits: List[Hit]) = hits.map(_.damage).sum * hits.map(_.multiplier).sum

  def ark(hits: List[Hit]): BigDecimal = {
    if (hits.isEmpty) {
      0
    } else {
      val prev = hits.take(hits.size - 1)
      val last = hits.last
      if (!prev.contains(last)) ark(prev) + sum(hits) else 2 * ark(prev)
    }
  }
}

case class Hit(val trap: Trap, val bonusMultiplier: BigDecimal = 0.0) {
  def damage = trap.damage
  def baseMultiplier = trap.multiplier
  def multiplier = baseMultiplier + bonusMultiplier
}