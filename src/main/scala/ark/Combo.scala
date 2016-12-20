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
  def isFeasible = Combo.isFeasible(this);
  override def toString = hits map { hit => s"(${hit.trap.name}, ${hit.bonusMultiplier})" } mkString
}

case object Combo {

  def ark(hits: Traversable[Hit]) = {
    val uniqueHits = hits.groupBy { _.trap }.map { _._2.head }
    val bdArk = uniqueHits.map { _.damage }.sum * uniqueHits.map { _.multiplier }.sum
    bdArk.toInt
  }

  /**
   * http://www7b.biglobe.ne.jp/~utherpendragon/kagero3.html
   */
  def scores(combo: Combo) = {

    case class Scores(elaborate: Int, sadistic: Int, humiliating: Int, ark: Int)

    var hasElaborate, hasSadistic, hasHumiliating = false
    var elaborate, sadistic, humiliating = 0
    var cumulativeElaborate, cumulativeSadistic, cumulativeHumiliating = 0
    var cumulativeMultiplier = BigDecimal(0)
    var cumulativeElaborateMultipliers, cumulativeSadisticMultipliers, cumulativeHumiliatingMultipliers: Seq[BigDecimal] = Nil

    for { hit <- combo.hits } {

      cumulativeMultiplier += hit.multiplier

      hit.align match {

        case Elaborate => {
          elaborate = cumulativeElaborateMultipliers.map { x => (x * cumulativeElaborate).toInt }.sum + ((cumulativeElaborate + hit.points) * cumulativeMultiplier).toInt
          cumulativeElaborate = (cumulativeElaborate + hit.points)
          cumulativeElaborateMultipliers = Nil
          hasElaborate = true
          if (hasSadistic) {
            cumulativeSadisticMultipliers match {
              case Nil            => cumulativeSadisticMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeSadisticMultipliers :+= (last + hit.multiplier)
            }
          }
          if (hasHumiliating) {
            cumulativeHumiliatingMultipliers match {
              case Nil            => cumulativeHumiliatingMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeHumiliatingMultipliers :+= (last + hit.multiplier)
            }
          }
        }

        case Sadistic => {
          sadistic = cumulativeSadisticMultipliers.map { x => (x * cumulativeSadistic).toInt }.sum + ((cumulativeSadistic + hit.points) * cumulativeMultiplier).toInt
          cumulativeSadistic = (cumulativeSadistic + hit.points)
          cumulativeSadisticMultipliers = Nil
          hasSadistic = true
          if (hasElaborate) {
            cumulativeElaborateMultipliers match {
              case Nil            => cumulativeElaborateMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeElaborateMultipliers :+= (last + hit.multiplier)
            }
          }
          if (hasHumiliating) {
            cumulativeHumiliatingMultipliers match {
              case Nil            => cumulativeHumiliatingMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeHumiliatingMultipliers :+= (last + hit.multiplier)
            }
          }
        }

        case Humiliating => {
          humiliating = cumulativeHumiliatingMultipliers.map { x => (x * cumulativeHumiliating).toInt }.sum + ((cumulativeHumiliating + hit.points) * cumulativeMultiplier).toInt
          cumulativeHumiliating = (cumulativeHumiliating + hit.points)
          cumulativeHumiliatingMultipliers = Nil
          hasHumiliating = true
          if (hasSadistic) {
            cumulativeSadisticMultipliers match {
              case Nil            => cumulativeSadisticMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeSadisticMultipliers :+= (last + hit.multiplier)
            }
          }
          if (hasElaborate) {
            cumulativeElaborateMultipliers match {
              case Nil            => cumulativeElaborateMultipliers :+= cumulativeMultiplier
              case (init :+ last) => cumulativeElaborateMultipliers :+= (last + hit.multiplier)
            }
          }
        }
      }
    }

    val ark = Combo.ark(combo.hits)

    Scores(elaborate, sadistic, humiliating, ark)
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

    // TODO: slows down a lot
    def allPairsValid = traps
      .sliding(2)
      .collect {
        case pair => {
          !isValidSequence(pair(0), pair(1))
        }
      }
      .isEmpty

    allPairsValid
  }

  def isFeasible(combo: Combo): Boolean = isFeasible(combo.hits.map { hit => hit.trap });
}