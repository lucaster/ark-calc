package ark.optaplanner.cloudbalancing

import java.util.Collection
import org.optaplanner.core.api.domain.solution.Solution

class CloudBalance extends Solution[MyScore] {
  override def getScore: MyScore = ???
  override def setScore(s: MyScore): Unit = ???
  override def getProblemFacts: Collection[MyScore] = ???
}