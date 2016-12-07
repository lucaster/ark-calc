package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class TwoComboSpec extends FunSpec {

  describe("A SpringFloor (Perfect Aim) -> Push Wall (Perfect Aim) Combo") {

    val hit1 = new Hit(Springboard, 0.1);
    val hit2 = new Hit(WallNudge, 0.1);

    val combo = new Combo(List(hit1, hit2));

    it("has two hits") {
      assert(2 === combo.hits.size);
    }

    it("has 21 ark") {
      assert(combo.ark === 21);
    }
  }

  describe("A 2 x SpringFloor (Perfect Aim) Combo") {

    val hit1, hit2 = new Hit(Springboard, 0.1);

    val combo = new Combo(List(hit1, hit2));

    it("has two hits") {
      assert(2 === combo.hits.size);
    }

    it("has 4 ark") {
      assert(combo.ark === 4);
    }
  }
}