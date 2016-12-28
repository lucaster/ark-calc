package ark

import org.scalatest.FunSpec

class ComboIsFeasibleSpec extends FunSpec {
  describe("A one hit Combo") {
    it("is always feasible") {
      for (trap <- Trap.values) {
        val combo = Combo(List(Hit(trap)))
        assert(combo.isFeasible)
      }
    }
  }
}