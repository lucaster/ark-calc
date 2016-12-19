package ark.optaplanner.cloudbalancing

import java.util.ArrayList
import java.util.Collection

import scala.beans.BeanProperty

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.Solution
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
case class CloudBalance() extends Solution[HardSoftScore] {

  /**
   * List of @PlanningVariable, as indicated in the @PlanningEntity
   */
  @ValueRangeProvider(id = "computerRange")
  @BeanProperty
  var computerList: java.util.List[CloudComputer] = new ArrayList[CloudComputer]

  @PlanningEntityCollectionProperty
  @BeanProperty
  var processList: java.util.List[CloudProcess] = new ArrayList[CloudProcess]

  @BeanProperty
  var score: HardSoftScore = _

  /**
   * The getProblemFacts() method is only needed for score calculation with Drools.
   * It is not needed for the other score calculation types.
   */
  override def getProblemFacts: Collection[_] = new ArrayList[CloudComputer](computerList);
}