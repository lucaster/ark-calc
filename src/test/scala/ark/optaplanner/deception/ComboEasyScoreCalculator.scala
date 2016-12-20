package ark.optaplanner.deception

import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator
import ark.Trap

class ComboEasyScoreCalculator extends EasyScoreCalculator[Combo] {

  val maxHitNum = 12
  val minA = 1
  val minE = 1
  val minS = 1
  val minH = 1

  override def calculateScore(combo: Combo): HardSoftScore = {
    HardSoftScore.valueOf(hardScore(combo), softScore(combo))
  }

  private def hardScore(combo: Combo): Int = {
    maxHitNum - combo.getHitList.size
  }

  private def softScore(combo: Combo): Int = {
    val c = Util.toArkCombo(combo)
    val a = c.ark
    val e = c.elaborate
    val s = c.sadistic
    val h = c.humiliating
    a + e + s + h - minA - minE - minS - minH
  }
}