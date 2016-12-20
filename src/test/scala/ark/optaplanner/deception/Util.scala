package ark.optaplanner.deception

import scala.collection.JavaConverters
import scala.collection.JavaConverters._
import scala.collection.JavaConversions
import scala.collection.JavaConversions._

object Util {
  def toArkCombo(combo: Combo) = {
    ark.Combo(combo
      .getHitList
      .map { _.trap }
      .flatMap { Option[ark.Trap] }
      .map { new ark.Hit(_) })
  }
}