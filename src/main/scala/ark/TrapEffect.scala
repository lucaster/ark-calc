package ark

sealed trait TrapEffect
object TrapEffect {

  sealed trait Move extends TrapEffect

  sealed trait Slide extends Move // Only for Wall!

  sealed abstract class MoveX(squares: Int) extends Move

  sealed abstract class SlideX(squares: Int) extends MoveX(squares) with Slide

  case object Berserk extends TrapEffect
  /**
   * Example: BearTrap, VacuumWall
   */
  case object Bind extends TrapEffect
  case object Electric extends TrapEffect
  case object Enraging extends TrapEffect
  case object Enveloping extends TrapEffect
  case object Explode extends Move
  case object Fire extends TrapEffect
  case object Flammable extends TrapEffect
  case object ForcedArmorBreak extends TrapEffect
  /**
   * Also binds
   */
  case object Ice extends TrapEffect
  /**
   * Trap also hits one or more spots next to the one it's placed at.
   * Example: EvilStomp, SwingingHammer,
   */
  case object HitsNear extends TrapEffect
  case object Movable extends TrapEffect
  case object Move1 extends MoveX(1)
  case object Move2 extends MoveX(2)
  case object Move3 extends MoveX(3)
  case object Move4 extends MoveX(4)
  case object Move5 extends MoveX(5)
  case object Move6 extends MoveX(6)
  case object Move8 extends MoveX(8)
  case object MoveToWall extends Move
  case object Oil extends TrapEffect
  case object Projectile extends TrapEffect
  case object Roll extends TrapEffect
  case object Slide1 extends SlideX(1)
  case object Slide2 extends SlideX(2)
  case object Slow extends TrapEffect
  case object StageHit2 extends TrapEffect
  case object StageHit3 extends TrapEffect
  case object StageHit4 extends TrapEffect
  case object StageHit5 extends TrapEffect
}