package ark.optaplanner.deception
import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._
import org.scalatest.Finders
import org.scalatest.FunSpec
import org.optaplanner.core.api.solver.SolverFactory
import ark.Trap
import ark.Trap._
import ark.TrapType._
import ark.TrapEffect._

class ComboSpec extends FunSpec {

  describe("A Solved Combo") {

    val res = "comboSolverConfig.xml"
    val traps = Trap.values
      .filterNot { _.explodes }
      .filterNot { _.rolls }
      .filterNot { _.isProjectile }
      .filterNot { _.kind == Ceiling }
      .filterNot { _.effects.contains(MoveToWall) }
      .filter { _.movesVictim }

    val hitNum = 7

    lazy val solved = {
      val solver = SolverFactory.createFromXmlResource[Combo](res, getClass.getClassLoader).buildSolver()
      val unsolvedCombo = new ComboGenerator().createCombo(traps, hitNum)
      solver.solve(unsolvedCombo)
    }

    it("exists") {
      println(s"${solved}")
      println(s"Score: ${solved.score}")
      println(s"Scores: ${Util.toArkCombo(solved).scores}")
    }

    it(s"has max ${hitNum} hits") {
      assert(solved.getHitList.size <= hitNum)
    }

    it("has correct scores") {
      val combo = ark.Combo(Seq(ArrowSlit, MagnifyingGlass, ArrowSlit, Washbin, Aldebaran, CakeintheFace, LightningSpear).map(ark.Hit(_)))
      assert(combo.ark === 1439)
      assert(combo.elaborate === 15365)
      assert(combo.sadistic === 672)
      assert(combo.humiliating === 3450)
    }
  }
}