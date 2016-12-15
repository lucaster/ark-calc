package ark.optaplanner.cloudbalancing

import scala.collection.JavaConverters._

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator

class CloudBalancingEasyScoreCalculator extends EasyScoreCalculator[CloudBalance] {

  /**
   * A very simple implementation. The double loop can easily be removed by using Maps as shown in
   * {@link CloudBalancingMapBasedEasyScoreCalculator#calculateScore(CloudBalance)}.
   */

  override def calculateScore(cloudBalance: CloudBalance): HardSoftScore = {

    var hardScore = 0
    var softScore = 0

    // For each computer
    for (computer <- cloudBalance.getComputerList.asScala) {

      var cpuPowerUsage = 0
      var memoryUsage = 0
      var networkBandwidthUsage = 0
      var used = false

      // For each process on that computer:
      for (process <- cloudBalance.getProcessList.asScala if (process.getCloudComputer == computer)) {
        // The computer's resources get used up:
        cpuPowerUsage += process.getRequiredCpuPower
        memoryUsage += process.getRequiredMemory
        networkBandwidthUsage += process.getRequiredNetworkBandwidth
        used = true
      }

      // Calculate hard constraints: respect computer capacity
      val cpuPowerAvailable = computer.getCpuPower - cpuPowerUsage
      if (cpuPowerAvailable < 0) {
        hardScore += cpuPowerAvailable
      }
      val memoryAvailable = computer.getMemory - memoryUsage
      if (memoryAvailable < 0) {
        hardScore += memoryAvailable
      }
      val networkBandwidthAvailable = computer.getNetworkBandwidth - networkBandwidthUsage
      if (networkBandwidthAvailable < 0) {
        hardScore += networkBandwidthAvailable
      }

      // Calculate soft constraints: minimize cost
      if (used) {
        softScore -= computer.getCost
      }
    }

    HardSoftScore.valueOf(hardScore, softScore)
  }
}