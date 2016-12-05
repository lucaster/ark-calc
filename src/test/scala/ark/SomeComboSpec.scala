package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._
import scala.util.Random

class SomeComboSpec extends FunSpec {

  describe("A n x Combo") {

    val comboSize = 1;

    it("") {
      /*Trap.values.toStream
      .permutations
      .filter { perm => perm.size <= comboSize }
      .filter { perm => perm.filterNot { t => t.isLegal }.isEmpty }
      .map { traps => Combo(traps.toList map { trap => Hit(trap) }) }
      *
      */
    }
  }
}