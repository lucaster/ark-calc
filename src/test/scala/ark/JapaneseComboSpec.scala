package ark

import org.scalatest.Assertions
import org.scalatest.FunSpec
import ark.Trap._

class JapaneseComboSpec extends FunSpec {

  describe("The first combo example") {

    val combo = Combo(Seq[Hit](
      Hit(Springboard),
      Hit(Washbin, 0.1),
      Hit(WallNudge, 0.1),
      Hit(PumpkinMask),
      Hit(LethalLance, 0.1),
      Hit(Vase),
      Hit(IronRake, 0.1),
      Hit(BearTrap),
      Hit(SwingingAxe, 0.1)))

    val ark = 1251
    val elaborate = 3636
    val humiliating = 3862
    val sadistic = 2417

    it(s"Has ${ark} Ark") {
      assert(combo.ark === ark)
    }
    it(s"Has ${elaborate} Elaborate") {
      assert(combo.elaborate === elaborate)
    }
    it(s"Has ${humiliating} Humiliating") {
      assert(combo.humiliating === humiliating)
    }
    it(s"Has ${sadistic} Sadistic") {
      assert(combo.sadistic === sadistic)
    }
  }
}