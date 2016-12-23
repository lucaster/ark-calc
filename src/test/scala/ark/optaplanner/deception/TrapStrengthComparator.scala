package ark.optaplanner.deception

import ark.Trap
import java.util.Comparator
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory

class TrapStrengthComparator extends Comparator[Trap] {

  override def compare(t1: Trap, t2: Trap) = ???

}

class Foo extends SelectionSorterWeightFactory[Combo, Trap] {

  override def createSorterWeight(combo: Combo, trap: Trap) = ???
}