package ark.optaplanner.deception

import scala.collection.JavaConverters.seqAsJavaListConverter

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution

import ark.Trap

class ComboGenerator {
  def createCombo(traps: Set[Trap], hitNum: Int): Combo = {
    val combo = new Combo
    combo.setTrapList(traps.toList.asJava)
    combo.setHitList(List.fill(hitNum)(new Hit).asJava)
    // Notice that we leave the PlanningVariable properties on null
    combo
  }
}