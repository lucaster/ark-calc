package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class OneComboSpec extends FunSpec {

  describe("An one hit Combo") {

    val hit = new Hit(SpringFloor, 0.1);
    val combo = new Combo(List(hit));

    it("has one hit") {
      assert(1 === combo.hits.size);
    }
    
    it("has the hit's ark") {
      assert(combo.ark === hit.damage * (hit.baseMultiplier + hit.bonusMultiplier));
    }
  }
}