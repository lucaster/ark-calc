package ark

sealed trait TrapEffect
object TrapEffect {

  sealed trait Move extends TrapEffect

  sealed trait Slide extends Move // Only for Wall!

  sealed abstract class MoveX(squares: Int) extends Move

  sealed abstract class SlideX(squares: Int) extends MoveX(squares) with Slide

  /**
   * Example: BearTrap, VacuumWall
   */
  case object Bind extends TrapEffect
  case object Electricity extends TrapEffect
  case object Enrage extends TrapEffect
  case object Fire extends TrapEffect
  case object ForcedArmorBreak extends TrapEffect
  case object Freeze extends TrapEffect
  /**
   * Trap also hits one or more spots next to the one it's placed at.
   * Example: EvilStomp, SwingingHammer,
   */
  case object HitsNear extends TrapEffect
  case object Move1 extends MoveX(1)
  case object Move2 extends MoveX(2)
  case object Move3 extends MoveX(3)
  case object Move4 extends MoveX(4)
  case object Move6 extends MoveX(6)
  case object MoveToWall extends Move
  case object Oil extends TrapEffect
  case object Projectile extends TrapEffect
  case object Roll extends TrapEffect
  case object Slide1 extends SlideX(1)
  case object Slide2 extends SlideX(2)
  case object StageHit4 extends TrapEffect
  case object StageHit7 extends TrapEffect
}