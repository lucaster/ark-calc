package ark

import org.scalatest.FunSpec

class FeasibleComboGeneratorSpec extends FunSpec {

  class ComboGenerator(val traps: Set[Trap]) {
    def stream(length: Int): Stream[Combo] = Stream[Combo](new Combo)
  }

  describe("A Combo Generator") {

    describe("when given some traps") {

      val traps = Trap.values.take(5)

      def comboGenerator = new ComboGenerator(traps)

      describe("and a desired Combo length") {

        val length = 7

        // Act
        val combos = comboGenerator.stream(length)

        it("produces a Stream of Combos.") {
          assert(combos.isInstanceOf[Stream[Combo]])
        }

        it("produces at least one Combo") {
          assert(combos.headOption.isDefined)
        }

        it("produces combos not longer than the given length") {
          assert(combos.filter { _.hits.length > length }.isEmpty)
        }

        it("produces only feasible combos") {
          assert(combos.filterNot { _.isFeasible }.isEmpty)
        }

        it("uses each given Trap once") {
          for { combo <- combos } {
            assert(combo.hits.size === combo.hits.toSet.size)
          }
        }
      }
    }
  }
}