package ark.optaplanner.cloudbalancing

import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._

import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.solver.SolverFactory
import org.scalatest.FunSpec

class OptaTest extends FunSpec {

  var score: HardSoftScore = _

  describe("a") {
    it("a") {
      val res = "cloudBalancingSolverConfig.xml"
      val solverFactory = SolverFactory.createFromXmlResource[CloudBalance](res)
      val comNum = 5
      val procNum = 20
      val solver = solverFactory.buildSolver()
      val unsolvedCloudBalance = new CloudBalancingGenerator().createCloudBalance(comNum, procNum)
      val solvedCloudBalance = solver.solve(unsolvedCloudBalance)

      println(s"\nSolved cloudBalance with ${comNum} computers and ${procNum} processes:\n${toDisplayString(solvedCloudBalance)}")
      println(s"Score: ${solvedCloudBalance.getScore}")
      def toDisplayString(cloudBalance: CloudBalance) = {
        cloudBalance.getProcessList
          .map { process =>
            s"${process} -> ${process.getCloudComputer}"
          }
          .mkString("\n")
      }
    }
  }
}