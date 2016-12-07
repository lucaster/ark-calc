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

    it("has ark = ???") {
      ???
    }
  }
}