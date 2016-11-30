package ark

sealed trait TrapAlign

object TrapAlign {

  case object Elaborate extends TrapAlign
  case object Humiliating extends TrapAlign
  case object Sadistic extends TrapAlign

  def parse(s: String) = s match {
    case s if s.equalsIgnoreCase("Elaborate")   => Some(Elaborate)
    case s if s.equalsIgnoreCase("Humiliating") => Some(Humiliating)
    case s if s.equalsIgnoreCase("Sadistic")    => Some(Sadistic)
    case _                                      => None
  }
}