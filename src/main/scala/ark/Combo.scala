package ark

import scala.math.BigDecimal.int2bigDecimal

import ark.Trap._
import ark.TrapType._
import ark.TrapAlign._
import ark.TrapEffect._
import scala.math.ScalaNumber

case class Combo(val hits: Seq[Hit] = Nil) {
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

    type Scorer = Hit => Int

    def scoreFor(scorer: Scorer): Int = {

      case class State(pointSum: Int, multiSum: BigDecimal, score: Int)

      combo
        .hits
        .foldLeft(State(0, 0, 0))((prevState, hit) => {
          val currPoint = scorer(hit)
          val currMulti = hit.multiplier
          val currPointSum = prevState.pointSum + currPoint
          val currMultiSum = prevState.multiSum + currMulti
          val hitScore = (currPointSum * currMultiSum).toInt
          val prevScore = prevState.score
          val currScore = prevScore + hitScore
          State(currPointSum, currMultiSum, currScore)
        })
        .score
    }

    case class Score(ark: Int, elaborate: Int, sadistic: Int, humiliating: Int) {
      override def toString = {
        s"Score(ark: ${ark}, elaborate: ${elaborate}, sadistic: ${sadistic}, humiliating: ${humiliating})"
      }
    }

    Score(scoreFor(_.damage), scoreFor(_.elaborate), scoreFor(_.sadistic), scoreFor(_.humiliating))
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