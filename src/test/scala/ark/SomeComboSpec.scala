package ark

import org.scalatest._

import ark.Trap._

class FloorSpec extends FunSpec {

  describe("BigDecimal 1.9") {

    val bd = BigDecimal("1.9")

    it("toInt = 1") {
      assert(bd.toInt === 1)
    }
  }

  describe("Double 1.9") {

    val d = 1.9

    it("toInt = 1") {
      assert(d.toInt === 1)
    }
  }
}