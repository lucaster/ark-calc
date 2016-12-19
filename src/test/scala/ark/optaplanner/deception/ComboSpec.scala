package ark.optaplanner.deception
import scala.collection.JavaConverters.asScalaBufferConverter
import org.scalatest.Finders
import org.scalatest.FunSpec
import org.optaplanner.core.api.solver.SolverFactory
import ark.Trap
import org.scalatest.BeforeAndAfter

class ComboSpec extends FunSpec with BeforeAndAfter {

  describe("A Solved Combo") {

    val res = "comboSolverConfig.xml"
    val traps = Trap.values
    val hitNum = 12

    it("hook") {

    }

    it("exists") {
      val solver = SolverFactory.createFromXmlResource[Combo](res).buildSolver()
      val unsolvedCombo = new ComboGenerator().createCombo(traps, hitNum)
      val solvedCombo = solver.solve(unsolvedCombo)

      println(s"\nSolved combo with ${traps.size} traps and ${hitNum} hits:\n${toDisplayString(solvedCombo)}")
      println(s"Score: ${solvedCombo.getScore}")

      def toDisplayString(combo: Combo) = {
        combo.getHitList.asScala
          .map { hit =>
            s"${hit} -> ${hit.getTrap} ${hit.getBonusMultiplier}"
          }
          .mkString("\n")
      }
    }

    it(s"has ${hitNum} hits") {
      val solver = SolverFactory.createFromXmlResource[Combo](res).buildSolver()
      val unsolvedCombo = new ComboGenerator().createCombo(traps, hitNum)
      val solvedCombo = solver.solve(unsolvedCombo)
      assert(solvedCombo.getHitList.size == hitNum)
    }

  }
}