package ark

sealed trait TrapEffect
object TrapEffect {

  case object Push1 extends TrapEffect
  case object Push2 extends TrapEffect
  case object Push3 extends TrapEffect
  case object Push4 extends TrapEffect
  case object Push6 extends TrapEffect
  case object Push1Side extends TrapEffect
  case object Push2Side extends TrapEffect
  case object Pull1 extends TrapEffect
  case object Pull2 extends TrapEffect
  case object Pull3 extends TrapEffect
  case object Bind extends TrapEffect
}