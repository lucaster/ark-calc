package ark

sealed trait TrapEffect
object TrapEffect {

  sealed trait Move extends TrapEffect

  sealed trait Slide extends Move // Only for Wall!

  sealed abstract class MoveX(squares: Int) extends Move

  sealed abstract class SlideX(squares: Int) extends MoveX(squares) with Slide

  case object Bind extends TrapEffect
  case object OnSpot extends TrapEffect
  case object Projectile extends TrapEffect
  case object Move1 extends MoveX(1)
  case object Move2 extends MoveX(2)
  case object Move3 extends MoveX(3)
  case object Move4 extends MoveX(4)
  case object Move6 extends MoveX(6)
  case object Slide1 extends SlideX(1)
  case object Slide2 extends SlideX(2)
  case object MoveToWall extends Move
}