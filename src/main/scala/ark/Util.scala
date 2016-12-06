package ark

import scala.Stream
import scala.annotation.tailrec

object Util {

  def powerset[A](s: Set[A]): Iterable[Set[A]] = {
    @tailrec
    def powerset_rec(acc: Stream[Set[A]], remaining: Set[A]): Stream[Set[A]] =
      if (remaining.isEmpty)
        acc
      else
        powerset_rec(acc ++ acc.map(_ + remaining.head), remaining.tail)

    powerset_rec(Stream(Set.empty), s)
  }
}