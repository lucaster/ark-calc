package ark.optaplanner.deception

import scala.beans.BeanProperty
import scala.math.BigDecimal.int2bigDecimal

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable

import ark.Trap

@PlanningEntity
case class Hit(val t: Trap,
               @BeanProperty var bonusMultiplier: BigDecimal) {

  setTrap(t);

  def this() = this(null, 0)

  @BeanProperty
  @PlanningVariable(valueRangeProviderRefs = Array("trapRange"))
  var trap: Trap = _
}