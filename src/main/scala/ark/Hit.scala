package ark

import scala.math.BigDecimal.double2bigDecimal
import ark.TrapAlign._

case class Hit(val trap: Trap, val bonusMultiplier: BigDecimal = 0.0) {
  def align = trap.align
  def damage = trap.damage

  def elaborate = points(Elaborate)
  def sadistic = points(Sadistic)
  def humiliating = points(Humiliating)

  private def points(trapAlign: TrapAlign): Int = {
    if (trap.align == trapAlign) {
      points
    }
    else {
      0
    }
  }

  def points = trap.points
  def baseMultiplier = trap.multiplier
  def multiplier = baseMultiplier + bonusMultiplier
  override def toString = s"(${trap.name}${if (bonusMultiplier == 0) "" else s""", ${bonusMultiplier}"""})"
}