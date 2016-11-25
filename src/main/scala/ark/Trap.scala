package ark

/**
 * Enum
 */
sealed abstract class Trap(val damage: Int, val multiplier: Double) {

  case object SpringBoard extends Trap(5, 0.7)
}