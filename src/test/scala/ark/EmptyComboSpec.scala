package ark

import org.scalatest.Assertions._
import org.scalatest.FunSpec
import ark.Trap._

class EmptyComboSpec extends FunSpec {

  describe("An empty Combo") {

    // Arrange
    // Act
    val combo = new Combo();

    it("has 0 ark") {
      // Assert
      assert(0 === combo.ark);
    }

    it("has no hits") {
      // Assert
      assert(combo.hits.isEmpty);
    }
  }
}