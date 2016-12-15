package ark.optaplanner.cloudbalancing

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable

import scala.beans.BeanProperty

@PlanningEntity
class CloudProcess(@BeanProperty var requiredCpuPower: Int,
                   @BeanProperty var requiredMemory: Int,
                   @BeanProperty var requiredNetworkBandwidth: Int,
                   val com: CloudComputer) {

  setCloudComputer(com)

  def this() = this(0, 0, 0, null)

  @BeanProperty
  @PlanningVariable(valueRangeProviderRefs = Array("computerRange"))
  var cloudComputer: CloudComputer = _

  // ************************************************************************
  // Complex methods
  // ************************************************************************
}