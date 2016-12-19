package ark.optaplanner.deception

import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

class ComboEasyScoreCalculator extends EasyScoreCalculator[Combo] {

  override def calculateScore(combo: Combo): HardSoftScore = {
    val hardScore = ???
    val softScore = ???
    HardSoftScore.valueOf(hardScore, softScore)
  }
}