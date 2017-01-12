package ark

import org.scalatest.FunSpec
import ark.Trap._
import ark._

trait ScoreCalculationSpec extends FunSpec {
  def describeH(combo: Combo, humiliating: Int) {
    describe(s"$combo") {
      it(s"has $humiliating humiliating") {
        assert(combo.humiliating === humiliating)
      }
    }
  }
}

class ScoreCalculationSpec1 extends ScoreCalculationSpec {
  describeH(Combo(Seq(Hit(BananaPeel))), 130)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask))), 509)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(HorseHead))), 1415)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(HorseHead), Hit(Tombstone))), 2818)
}
class ScoreCalculationSpec2 extends ScoreCalculationSpec {
  describeH(Combo(Seq(Hit(BananaPeel))), 130)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask))), 509)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase))), 509)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase), Hit(HorseHead))), 2204)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase), Hit(HorseHead), Hit(Tombstone))), 3912)
}
class ScoreCalculationSpec2b extends ScoreCalculationSpec {
  describeH(Combo(Seq(Hit(BananaPeel))), 130)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask))), 509)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase))), 509)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase), Hit(GenocideEye))), 2204)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase), Hit(GenocideEye), Hit(HorseHead))), 3282)
  describeH(Combo(Seq(Hit(BananaPeel), Hit(PumpkinMask), Hit(Vase), Hit(GenocideEye), Hit(HorseHead), Hit(Tombstone))), 5386)
}
class ScoreCalculationSpec3 extends ScoreCalculationSpec {
  describeH(Combo(Seq(Hit(BidetToilet))), ???)
  describeH(Combo(Seq(Hit(BidetToilet), Hit(Hotplate))), ???)
  describeH(Combo(Seq(Hit(BidetToilet), Hit(Hotplate), Hit(DeltaHorse))), ???)
}