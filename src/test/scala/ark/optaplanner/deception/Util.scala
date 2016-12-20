package ark.optaplanner.deception

import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._

import ark.Trap

object Util {
  def toArkCombo(combo: Combo) = {
    val hits = combo
      .getHitList
      .map { _.trap }
      .flatMap(Option[Trap]) // exclude nulls
      .map { trap => ark.Hit(trap) }
    ark.Combo(hits)
  }
}