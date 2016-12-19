package ark.optaplanner.cloudbalancing

import java.util.Random

import scala.beans.BeanProperty
import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.seqAsJavaListConverter

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution
import java.util.UUID

class CloudBalancingGenerator {

  def createCloudBalance(computerListSize: Int, processListSize: Int): CloudBalance = {
    createCloudBalance(determineFileName(computerListSize, processListSize), computerListSize, processListSize)
  }

  def createCloudBalance(inputId: String, computerListSize: Int, processListSize: Int): CloudBalance = {
    random = new Random(47)
    val cloudBalance = new CloudBalance
    // cloudBalance.setId(inputId)
    cloudBalance.setComputerList(createComputerList(computerListSize))
    cloudBalance.setProcessList(createProcessList(processListSize))
    assureComputerCapacityTotalAtLeastProcessRequiredTotal(cloudBalance)
    return cloudBalance;
  }

  private def determineFileName(computerListSize: Int, processListSize: Int): String = {
    s"${computerListSize} computers - ${processListSize} processes"
  }

  /**
   * This ensures that the problem will be solvable
   */
  private def assureComputerCapacityTotalAtLeastProcessRequiredTotal(cloudBalance: CloudBalance): Unit = {

    val computerList = cloudBalance.getComputerList

    var cpuPowerTotal = 0
    var memoryTotal = 0
    var networkBandwidthTotal = 0

    for (computer <- computerList.asScala) {
      cpuPowerTotal += computer.getCpuPower
      memoryTotal += computer.getMemory
      networkBandwidthTotal += computer.getNetworkBandwidth
    }

    var requiredCpuPowerTotal = 0
    var requiredMemoryTotal = 0
    var requiredNetworkBandwidthTotal = 0

    for (process <- cloudBalance.getProcessList.asScala) {
      requiredCpuPowerTotal += process.getRequiredCpuPower
      requiredMemoryTotal += process.getRequiredMemory
      requiredNetworkBandwidthTotal += process.getRequiredNetworkBandwidth
    }

    var cpuPowerLacking = requiredCpuPowerTotal - cpuPowerTotal

    while (cpuPowerLacking > 0) {
      val computer = computerList.get(random.nextInt(computerList.size))
      val upgrade = determineUpgrade(cpuPowerLacking)
      computer.setCpuPower(computer.getCpuPower + upgrade)
      cpuPowerLacking -= upgrade;
    }

    var memoryLacking = requiredMemoryTotal - memoryTotal

    while (memoryLacking > 0) {
      val computer = computerList.get(random.nextInt(computerList.size))
      val upgrade = determineUpgrade(memoryLacking)
      computer.setMemory(computer.getMemory + upgrade)
      memoryLacking -= upgrade
    }

    var networkBandwidthLacking = requiredNetworkBandwidthTotal - networkBandwidthTotal

    while (networkBandwidthLacking > 0) {
      val computer = computerList.get(random.nextInt(computerList.size))
      val upgrade = determineUpgrade(networkBandwidthLacking)
      computer.setNetworkBandwidth(computer.getNetworkBandwidth + upgrade)
      networkBandwidthLacking -= upgrade
    }
  }

  private def determineUpgrade(lacking: Int): Int = {
    for (upgrade <- Array(8, 4, 2, 1)) {
      if (lacking >= upgrade) {
        return upgrade
      }
    }
    throw new IllegalStateException(s"""Lacking ("$lacking") should be at least 1.""")
  }

  private def createComputerList(computerListSize: Int) = {

    def createRandomComputer = {
      val computer = new CloudComputer
      val name = UUID.randomUUID().toString
      val cpuPowerPricesIndex = random.nextInt(CPU_POWER_PRICES.length)
      val memoryPricesIndex: Int = distortIndex(cpuPowerPricesIndex, MEMORY_PRICES.length)
      val networkBandwidthPricesIndex: Int = distortIndex(cpuPowerPricesIndex, NETWORK_BANDWIDTH_PRICES.length)
      val cost = CPU_POWER_PRICES(cpuPowerPricesIndex).getCost + MEMORY_PRICES(memoryPricesIndex).getCost + NETWORK_BANDWIDTH_PRICES(networkBandwidthPricesIndex).getCost
      computer.setName(name)
      computer.setCpuPower(CPU_POWER_PRICES(cpuPowerPricesIndex).getHardwareValue)
      computer.setMemory(MEMORY_PRICES(memoryPricesIndex).getHardwareValue)
      computer.setNetworkBandwidth(NETWORK_BANDWIDTH_PRICES(networkBandwidthPricesIndex).getHardwareValue)
      computer.setCost(cost)
      computer
    }

    (1 to computerListSize).map { _ => createRandomComputer }.asJava
  }

  private def createProcessList(processListSize: Int) = {

    def createRandomProcess = {
      val process: CloudProcess = new CloudProcess
      //process.setId((long) i)
      val requiredCpuPower: Int = generateRandom(MAXIMUM_REQUIRED_CPU_POWER)
      val requiredMemory: Int = generateRandom(MAXIMUM_REQUIRED_MEMORY)
      val requiredNetworkBandwidth: Int = generateRandom(MAXIMUM_REQUIRED_NETWORK_BANDWIDTH)
      process.setRequiredCpuPower(requiredCpuPower)
      process.setRequiredMemory(requiredMemory)
      process.setRequiredNetworkBandwidth(requiredNetworkBandwidth)
      // Notice that we leave the PlanningVariable properties on null
      process
    }

    (1 to processListSize).map { _ => createRandomProcess }.asJava
  }

  private def distortIndex(referenceIndex: Int, length: Int): Int = {
    val randomDouble = random.nextDouble
    var index = referenceIndex
    var loweringThreshold = 0.25
    while (randomDouble < loweringThreshold && index >= 1) {
      index -= 1
      loweringThreshold *= 0.10;
    }
    var heighteningThreshold = 0.75
    while (randomDouble >= heighteningThreshold && index <= (length - 2)) {
      index += 1;
      heighteningThreshold = 1.0 - (1.0 - heighteningThreshold) * 0.10;
    }
    index
  }

  private def generateRandom(maximumValue: Int): Int = {
    val randomDouble = random.nextDouble
    val parabolaBase = 2000.0
    val parabolaRandomDouble = (Math.pow(parabolaBase, randomDouble) - 1.0) / (parabolaBase - 1.0)
    if (parabolaRandomDouble < 0.0 || parabolaRandomDouble >= 1.0) {
      throw new IllegalArgumentException("Invalid generated parabolaRandomDouble (" + parabolaRandomDouble + ")")
    }
    val value = (Math.floor(parabolaRandomDouble * maximumValue) + 1).toInt
    if (value < 1 || value > maximumValue) {
      throw new IllegalArgumentException("Invalid generated value (" + value + ")")
    }
    value
  }

  private var random: Random = _

  private val CPU_POWER_PRICES: Array[Price] = Array(
    new Price(3, "single core 3ghz", 110),
    new Price(4, "dual core 2ghz", 140),
    new Price(6, "dual core 3ghz", 180),
    new Price(8, "quad core 2ghz", 270),
    new Price(12, "quad core 3ghz", 400),
    new Price(16, "quad core 4ghz", 1000),
    new Price(24, "eight core 3ghz", 3000))

  private val MEMORY_PRICES: Array[Price] = Array(
    new Price(2, "2 gigabyte", 140),
    new Price(4, "4 gigabyte", 180),
    new Price(8, "8 gigabyte", 220),
    new Price(16, "16 gigabyte", 300),
    new Price(32, "32 gigabyte", 400),
    new Price(64, "64 gigabyte", 600),
    new Price(96, "96 gigabyte", 1000))

  private val NETWORK_BANDWIDTH_PRICES: Array[Price] = Array(
    new Price(2, "2 gigabyte", 100),
    new Price(4, "4 gigabyte", 200),
    new Price(6, "6 gigabyte", 300),
    new Price(8, "8 gigabyte", 400),
    new Price(12, "12 gigabyte", 600),
    new Price(16, "16 gigabyte", 800),
    new Price(20, "20 gigabyte", 1000))

  private val MAXIMUM_REQUIRED_CPU_POWER = 12
  private val MAXIMUM_REQUIRED_MEMORY = 32
  private val MAXIMUM_REQUIRED_NETWORK_BANDWIDTH = 12

  private case class Price(@BeanProperty hardwareValue: Int,
                           @BeanProperty description: String,
                           @BeanProperty cost: Int)
}