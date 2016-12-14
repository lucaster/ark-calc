package ark.optaplanner.cloudbalancing

import scala.beans.BeanProperty

case class CloudComputer(@BeanProperty var cpuPower: Int,
                         @BeanProperty var memory: Int,
                         @BeanProperty var networkBandwidth: Int,
                         @BeanProperty var cost: Int) {

  def this() = this(0, 0, 0, 0)

}