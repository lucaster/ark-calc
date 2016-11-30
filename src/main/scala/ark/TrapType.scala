package ark

sealed trait TrapType

object TrapType {

  case object Ceiling extends TrapType
  case object Floor extends TrapType
  case object Wall extends TrapType

  def parse(s: String) = s match {
    case s if s.equalsIgnoreCase("Ceiling") => Some(Ceiling)
    case s if s.equalsIgnoreCase("Floor")   => Some(Floor)
    case s if s.equalsIgnoreCase("Wall")    => Some(Wall)
    case _                                  => None
  }
}