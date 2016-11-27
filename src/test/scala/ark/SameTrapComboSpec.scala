package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class SameTrapComboSpec extends FunSpec {

  describe("A n x (Trap) combo") {

    val n = 15;

    val hits = (1 to n).toList.map(_ => new Hit(Springboard, 0.1));

    val combo = new Combo(hits);

    it("has n hits") {
      assert(n === combo.hits.size);
    }

    it("has ark = ark(hit)*2^(n-1)") {
      val first = hits.head
      assert(combo.ark === first.damage * first.multiplier * Math.pow(2, n - 1));
    }
  }
}