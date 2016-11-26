package ark

sealed trait TrapType
object TrapType {
  case object Elaborate extends TrapType
  case object Humiliating extends TrapType
  case object Sadistic extends TrapType
}

sealed trait TrapCategory
object TrapCategory {
  case object Ceiling extends TrapCategory
  case object Floor extends TrapCategory
  case object Wall extends TrapCategory
}

sealed trait Trap {
  def damage: Int
  def multiplier: BigDecimal
  def `type`: TrapType
}
object Trap {
  import TrapType._
  import TrapCategory._
  sealed abstract class BaseTrap(val damage: Int,
                                 val multiplier: BigDecimal,
                                 val `type`: TrapType,
                                 val category: TrapCategory) extends Trap
  case object SpringFloor extends BaseTrap(5, 0.7, Elaborate, Floor)
  case object PushWall extends BaseTrap(5, 1.2, Elaborate, Wall)
}