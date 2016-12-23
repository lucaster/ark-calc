package ark.optaplanner.deception

import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution

import ark.Trap

class ComboGenerator {
  def createCombo(trapRange: Set[Trap], hitNum: Int): Combo = {
    val combo = new Combo
    combo.setTrapList(trapRange.toList)
    combo.setHitList(List.fill(hitNum)(new Hit))
    // Notice that we leave the PlanningVariable properties on null
    combo
  }
}