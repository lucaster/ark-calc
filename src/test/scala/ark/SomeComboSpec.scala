package ark

import org.scalatest._

import ark.Trap._

class SomeComboSpec extends FunSpec {

  describe("A n x Combo") {

    val combo = Combo(Seq(ArrowSlit, MagnifyingGlass, ArrowSlit, Washbin, Aldebaran, Washbin, Washbin).map { new Hit(_) })

    it("Has scores") {
      assert(combo.ark === 1267)
      assert(combo.elaborate === 15632)
      assert(combo.sadistic === 672)
      assert(combo.humiliating === 3456)
    }
  }
}