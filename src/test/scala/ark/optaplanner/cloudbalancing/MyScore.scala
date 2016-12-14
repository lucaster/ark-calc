package ark.optaplanner.cloudbalancing

import org.optaplanner.core.api.score.AbstractScore

class MyScore extends AbstractScore[MyScore] {
  def add(s: MyScore): MyScore = ???
  def subtract(s: MyScore): MyScore = ???
  def compareTo(t: MyScore): Int = ???
  def divide(d: Double): MyScore = ???
  def multiply(d: Double): MyScore = ???
  def power(d: Double): MyScore = ???
  def negate: MyScore = ???
  def toLevelNumbers: Array[Number] = ???
}