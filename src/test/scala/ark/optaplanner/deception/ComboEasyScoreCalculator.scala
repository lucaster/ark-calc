package ark.optaplanner.deception

import scala.collection.JavaConversions._

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator

import ark.TrapAlign
import ark.TrapAlign._

object ComboEasyScoreCalculator {
  val minA = 1000
  val minE = 1000
  val minS = 1000
  val minH = 1000
}

class ComboEasyScoreCalculator extends EasyScoreCalculator[Combo] {

  import ComboEasyScoreCalculator._

  val maxHitNum = 12

  override def calculateScore(combo: Combo): HardSoftScore = {
    HardSoftScore.valueOf(hardScore(combo), softScore(combo))
  }

  private def hardScore(combo: Combo): Int = {

    var hard = 0

    val arkCombo = Util.toArkCombo(combo)

    // Combo should be feasible:
    if (!arkCombo.isFeasible) {
      hard -= 100
    }

    val traps = combo.getHitList.map { _.trap }

    // Each Trap should be used only once:
    hard -= traps.size - traps.distinct.size

    // The shorter the Combo, the better:
    hard += maxHitNum - combo.getHitList.size

    // TODO: same trap must be separated by 4 other traps at least

    hard
  }

  private def softScore(combo: Combo): Int = {

    var soft = 0

    val arkCombo = Util.toArkCombo(combo)

    // Mazimize scores
    val a = arkCombo.ark
    val e = arkCombo.elaborate
    val s = arkCombo.sadistic
    val h = arkCombo.humiliating
    val values = Set(a, e, s, h)
    val max = values.max
    val min = values.min
    val sum = values.sum
    val avg = sum / 4
    val diff = Set(a - minA, e - minE, s - minS, h - minH)
    val diffAvg = diff.sum / diff.size
    soft += avg

    // Prefer mix:
    def hasAlign(align: TrapAlign) = !arkCombo.hits.filter { _.trap.align == Elaborate }.isEmpty
    val hasE = hasAlign(Elaborate)
    val hasS = hasAlign(Sadistic)
    val hasH = hasAlign(Humiliating)
    if (hasE) soft += 500
    if (hasS) soft += 500
    if (hasH) soft += 500

    // Do not start with a projectile
    arkCombo.hits.headOption
      .map { _.trap }
      .map { _.isProjectile }
      .collect(_ match {
        case true => {
          soft -= 1000
        }
      })

    soft
  }
}