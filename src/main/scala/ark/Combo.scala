package ark

import scala.math.BigDecimal.int2bigDecimal

import ark.Trap._
import ark.TrapType._
import ark.TrapAlign._
import ark.TrapEffect._
import scala.math.ScalaNumber

case class Combo(val hits: Seq[Hit] = Nil) {
  // Charge Zero -> no ark
  // Auto Defense -> half ark, half points
  // Charge Zero, Auto Defense -> no ark, half points
  lazy val scores = Combo.scores(this)
  def ark = scores.ark
  def elaborate = scores.elaborate
  def humiliating = scores.humiliating
  def sadistic = scores.sadistic
  def isFeasible = Combo.isFeasible(this)
  def +(trap: Trap) = copy(hits :+ Hit(trap))
  override def toString = hits map { hit => s"(${hit.trap.name}, ${hit.bonusMultiplier})" } mkString
}

case object Combo {

  /**
   * http://www7b.biglobe.ne.jp/~utherpendragon/kagero3.html IS WRONG
   */
  def scores(combo: Combo) = {

    case class ArkState(pointSum: Int, multiSum: BigDecimal, score: Int) {
      require(pointSum >= 0)
      require(multiSum >= 0)
      require(score >= 0)
      require(score >= (pointSum * multiSum).toInt)
    }

    val arkScore = combo
      .hits
      .foldLeft(ArkState(0, 0, 0))((prevState, hit) => {
        val pointSum = prevState.pointSum + hit.damage
        val multiSum = prevState.multiSum + hit.multiplier
        val hitScore = (pointSum * multiSum).toInt
        val score = hitScore + prevState.score
        ArkState(pointSum, multiSum, score)
      })
      .score

    def scoreFor(align: TrapAlign): Int = {

      case class PointState(pointSum: Int, multiSum: BigDecimal, awayPointSum: Int, score: Int)

      val points: Hit => Int = align match {
        case Elaborate   => (hit => hit.elaborate)
        case Sadistic    => (hit => hit.sadistic)
        case Humiliating => (hit => hit.humiliating)
      }

      combo
        .hits
        .dropWhile { _.align != align }
        .foldLeft(PointState(0, 0, 0, 0))((prevState, hit) => {
          val pointSum = prevState.pointSum + points(hit)
          val multiSum = prevState.multiSum + hit.multiplier
          val alignHitScore = (pointSum * multiSum).toInt
          val hitScore = if (hit.align == align) alignHitScore + prevState.awayPointSum else 0
          val awayHitScore = if (hit.align == align) 0 else alignHitScore
          val awayPointSum = if (hit.align == align) 0 else prevState.awayPointSum + awayHitScore
          val score = hitScore + prevState.score
          val state = PointState(pointSum, multiSum, awayPointSum, score)
          def fmt(i: Int) = if (i == 0) "-" else i
          println(f"${hit.trap.name}%-8s\t${fmt(points(hit))}\t${hit.multiplier}\t${fmt(hitScore)}\t(${awayHitScore})\t[${awayPointSum}]\t${score}")
          state
        })
        .score
    }

    case class Score(ark: Int, elaborate: Int, sadistic: Int, humiliating: Int) {
      override def toString = {
        s"Score(ark: ${ark}, elaborate: ${elaborate}, sadistic: ${sadistic}, humiliating: ${humiliating})"
      }
    }

    Score(arkScore, scoreFor(Elaborate), scoreFor(Sadistic), scoreFor(Humiliating))
  }

  def isValidSequence(trap1: Trap, trap2: Trap): Boolean = {

    if (Set[TrapType](Floor, Ceiling).contains(trap1.kind) &&
      Set[TrapType](Floor, Ceiling).contains(trap2.kind)) {
      if (!trap1.effects.contains(HitsNear) && !trap2.effects.contains(HitsNear)) {
        if (!trap1.movesVictim /* && !trap2.movesVictim*/ ) {
          // TODO: è la prima che deve muovere, la seconda anche no
          // Test case: MagnifyingGlass, Washbin non va bene infatti
          return false
        }
      }
    }

    return true
  }

  def isFeasible(traps: Seq[Trap]): Boolean = {

    def allPairsValid = traps
      .sliding(2)
      .filter { _.size == 2 }
      .filter { pair => !isValidSequence(pair(0), pair(1)) }
      .isEmpty

    // TODO: cannot have same trap twice in a row!

    allPairsValid
  }

  def isFeasible(combo: Combo): Boolean = isFeasible(combo.hits.map { _.trap });
}