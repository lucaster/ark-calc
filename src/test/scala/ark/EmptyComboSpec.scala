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
    
    describe("When is added a hit") {
      // Act
      val newCombo = combo.append(new Hit(Springboard));
      
      it("returns a new instance") {
        // Assert
        assert(!newCombo.eq(combo));
      }
      it("has one hit") {
        // Assert
        assert(combo.hits.size + 1 === newCombo.hits.size);
      }
    }
  }
}