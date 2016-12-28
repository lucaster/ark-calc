package ark

import org.scalatest.FunSpec
import scala.annotation.tailrec

class FeasibleComboGeneratorSpec extends FunSpec {

  class ComboGenerator(traps: Set[Trap]) {

    def stream(length: Int): Stream[Combo] = {

      def expand(combo: Combo, candidates: Set[Trap]) =
        candidates
          .map { trap => (combo + trap, candidates - trap) }
          .filter { _._1.isFeasible }

      def expandTuple(tuple: (Combo, Set[Trap])) = expand(tuple._1, tuple._2)

      @tailrec
      def step(state: Stream[(Combo, Set[Trap])], togo: Int): Stream[(Combo, Set[Trap])] = {

        require { togo >= 0 }

        if (togo == 0) {
          state
        }
        else {
          step(state flatMap expandTuple, togo - 1)
        }
      }

      step(Stream((Combo(), traps)), length) map { _._1 }
    }
  }

  describe("A Combo Generator") {

    val traps = Trap.values

    describe(s"when given ${traps.size} traps") {

      val length = 3

      describe(s"and a desired Combo length of ${length}") {

        def comboGenerator = new ComboGenerator(traps)

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

        it("produces combos of size min(traps.size, length)") {
          for (combo <- combos) {
            assert(combo.hits.size === Math.min(traps.size, length))
          }
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