package ark.optaplanner.cloudbalancing

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.solver.SolverFactory
import org.scalatest.FunSpec

class OptaTest extends FunSpec {

  var score: HardSoftScore = _

  describe("a") {
    it("a") {

      val res = "cloudBalancingSolverConfig.xml"
      val solverFactory = SolverFactory.createFromXmlResource[CloudBalance](res)
      val solver = solverFactory.buildSolver()
      val unsolvedCloudBalance = new CloudBalancingGenerator().createCloudBalance(400, 1200)
      val solvedCloudBalance = solver.solve(unsolvedCloudBalance)

      println(s"\nSolved cloudBalance with 400 computers and 1200 processes:\n${toDisplayString(solvedCloudBalance)}")

      def toDisplayString(b: CloudBalance) = b.toString
    }
  }
}