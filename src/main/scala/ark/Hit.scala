package ark

import scala.math.BigDecimal.double2bigDecimal

case class Hit(val trap: Trap, val bonusMultiplier: BigDecimal = 0.0) {
  def damage = trap.damage
  def baseMultiplier = trap.multiplier
  def multiplier = baseMultiplier + bonusMultiplier
}