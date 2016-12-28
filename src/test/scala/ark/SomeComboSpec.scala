package ark

import org.scalatest._

import ark.Trap._

class SomeComboSpec extends FunSpec {

  describe("A n x Combo") {

    val combo = Combo(Seq(ArrowSlit, MagnifyingGlass, ArrowSlit, Washbin, Aldebaran, Washbin, Washbin).map { new Hit(_) })

    it("Has scores") {
      assert(combo.ark === 635)
      assert(combo.elaborate === 1972)
      assert(combo.sadistic === 672)
      assert(combo.humiliating === 4320)
    }
  }
}