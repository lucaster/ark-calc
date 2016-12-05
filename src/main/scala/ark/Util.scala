package ark

import scala.Stream
import scala.annotation.tailrec

object Util {

  def subsets(start: Int, end: Int, size: Int): Seq[Seq[Int]] =
    if (size == 0)
      List(Nil)
    else
      for {
        head <- (start to end)
        tail <- subsets(head + 1, end, size - 1)
      } yield head +: tail

  def powerset[A](s: Set[A]): Set[Set[A]] = {
    @tailrec
    def powerset_rec(acc: Set[Set[A]], remaining: Set[A]): Set[Set[A]] =
      if (remaining.isEmpty)
        acc
      else
        powerset_rec(acc ++ acc.map(_ + remaining.head), remaining.tail)

    powerset_rec(Set(Set.empty), s)
  }

  def powersetStream[A](s: Set[A]): Stream[Set[A]] = {
    @tailrec
    def powerset_rec(acc: Stream[Set[A]], remaining: Set[A]): Stream[Set[A]] =
      if (remaining.isEmpty)
        acc
      else
        powerset_rec(acc ++ acc.map(_ + remaining.head), remaining.tail)

    powerset_rec(Stream(Set.empty), s)
  }
}