package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._
import scala.util.Random

class SomeComboSpec extends FunSpec {

  describe("A n x Combo") {

    val n = 9;

    Trap.values.toStream
      .permutations
      .filter { _.size <= n }
      .map { traps => Combo(traps.toList map { trap => Hit(trap) }) }
  }
}