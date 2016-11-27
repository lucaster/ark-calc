package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class ThreeComboSpec extends FunSpec {
  
  describe("A 3 x SpringFloor (Perfect Aim) Combo") {

    val hit1, hit2, hit3 = new Hit(Springboard, 0.1);
    
    val combo = new Combo(List(hit1, hit2, hit3));

    it("has 3 hits") {
      assert(3 === combo.hits.size);
    }
    
    it("has 16 ark") {
      assert(combo.ark === 16);
    }
  }
}