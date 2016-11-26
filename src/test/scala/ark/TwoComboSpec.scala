package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class TwoComboSpec extends FunSpec {

  describe("A SpringFloor (Perfect Aim) -> Push Wall (Perfect Aim) Combo") {

    val hit1 = new Hit(SpringFloor, 0.1);
    val hit2 = new Hit(PushWall, 0.1);
    
    val combo = new Combo(List(hit1, hit2));

    it("has two hits") {
      assert(2 === combo.hits.size);
    }
    
    it("has 25 ark") {
      assert(combo.ark === 25);
    }
  }
  
  describe("A 2 x SpringFloor (Perfect Aim) Combo") {

    val hit1, hit2 = new Hit(SpringFloor, 0.1);
    
    val combo = new Combo(List(hit1, hit2));

    it("has two hits") {
      assert(2 === combo.hits.size);
    }
    
    it("has 8 ark") {
      assert(combo.ark === 8);
    }
  }
}