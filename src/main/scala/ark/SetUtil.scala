package ark

import scala.annotation.tailrec

object Util {

  def powerset[A](s: Set[A]): List[Set[A]] = {
    @tailrec
    def powerset_rec(acc: List[Set[A]], remaining: List[A]): List[Set[A]] =
      if (remaining.isEmpty)
        acc
      else
        powerset_rec(acc ++ acc.map(_ + remaining.head), remaining.tail)

    powerset_rec(List(Set.empty[A]), s.toList)
  }

  def powerList[A](l: List[A]): Set[List[A]] = {
    throw new UnsupportedOperationException
  }
}