package ark

case class Combo(private val _hits: List[Hit] = Nil) {

  /**
   * Current total ark
   */
  def ark: Int = {
    def ark(hits: List[Hit]): BigDecimal = {
      if (hits.isEmpty) {
        0
      } else {
        val prev = hits.take(hits.size - 1)
        val last = hits.last
        def sum(hits: List[Hit]) = hits.map(_.damage).sum * hits.map(_.multiplier).sum
        if (prev.contains(last)) 2 * ark(prev) else ark(prev) + sum(hits)
      }
    }
    ark(hits).toInt;
  }

  /**
   * Combo ark if the hit were added.
   */
  def ark(candidate: Hit): Int = throw new UnsupportedOperationException("Implement me!");

  def hits: List[Hit] = _hits;

  /**
   * Appends the hit at the end of the Combo.
   *
   * @return a new Combo instance
   */
  def append(hit: Hit): Combo = copy(_hits :+ hit)
}

case class Hit(val trap: Trap, val bonusMultiplier: BigDecimal = 0.0) {
  def damage = trap.damage
  def baseMultiplier = trap.multiplier
  def multiplier = baseMultiplier + bonusMultiplier
}