package ark.optaplanner.deception

import java.util.ArrayList
import java.util.Collection

import scala.beans.BeanProperty

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.Solution
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

import ark.Trap

@PlanningSolution
case class Combo() extends Solution[HardSoftScore] {

  // TODO: place here hard constraints

  /**
   * List of @PlanningVariable, as indicated in the @PlanningEntity
   */
  @ValueRangeProvider(id = "trapRange")
  @BeanProperty
  var trapList: java.util.List[Trap] = new ArrayList[Trap]

  @PlanningEntityCollectionProperty
  @BeanProperty
  var hitList: java.util.List[Hit] = new ArrayList[Hit]

  @BeanProperty
  var score: HardSoftScore = _

  /**
   * The getProblemFacts() method is only needed for score calculation with Drools.
   * It is not needed for the other score calculation types.
   */
  override def getProblemFacts: Collection[_] = new ArrayList[Trap](trapList);
}