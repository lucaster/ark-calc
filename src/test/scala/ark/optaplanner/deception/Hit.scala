package ark.optaplanner.deception

import scala.beans.BeanProperty
import scala.math.BigDecimal.int2bigDecimal

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable

import ark.Trap

@PlanningEntity
case class Hit() {

  def this(trap: Trap, bonusMultiplier: BigDecimal) = {
    this()
    this.trap = trap
    this.bonusMultiplier = bonusMultiplier
  }

  @BeanProperty
  var bonusMultiplier: BigDecimal = 0.0

  @PlanningVariable(valueRangeProviderRefs = Array("trapRange"))
  @BeanProperty
  var trap: Trap = _

  override def toString = s"(${trap.name}${if (bonusMultiplier == 0) "" else s""", ${bonusMultiplier}"""})"
}