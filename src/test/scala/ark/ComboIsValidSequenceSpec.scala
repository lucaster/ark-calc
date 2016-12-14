package ark

import org.scalatest.FunSpec

import ark.Trap._
import ark.TrapEffect._
import ark.TrapType._

class ComboIsValidSequenceSpec extends FunSpec {

  describe(SharkBlade.toString) {
    it("") {
      assert(!SharkBlade.movesVictim)
      assert(!SharkBlade.effects.contains(HitsNear))
      assert(SharkBlade.kind == Floor)
    }
  }
  describe(Launchpad.toString) {
    it("") {
      assert(!Launchpad.movesVictim)
      assert(!Launchpad.effects.contains(HitsNear))
      assert(Launchpad.kind == Floor)
    }
  }
  describe(Tombstone.toString) {
    it("") {
      assert(!Tombstone.movesVictim)
      assert(!Tombstone.effects.contains(HitsNear))
      assert(Tombstone.kind == Ceiling)
    }
  }
  describe(Boulder.toString) {
    it("") {
      assert(!Boulder.movesVictim)
      assert(!Boulder.effects.contains(HitsNear))
      assert(Boulder.kind == Ceiling)
    }
  }

  describe("A 2 trap sequence") {

    describe("Floor -> Floor without Move without HitNear") {
      val trap1 = SharkBlade
      val trap2 = Launchpad
      it("is not legal") {
        assert(Combo.isValidSequence(trap1, trap2) === false)
      }
    }
    describe("Ceiling -> Ceiling without Move without HitNear") {
      val trap1 = Tombstone
      val trap2 = Boulder
      it("is not legal") {
        assert(Combo.isValidSequence(trap1, trap2) === false)
      }
    }
    describe("Ceiling -> Floor without Move without HitNear") {
      val trap1 = Tombstone
      val trap2 = SharkBlade
      it("is not legal") {
        assert(Combo.isValidSequence(trap1, trap2) === false)
      }
    }
    describe("Floor -> Ceiling without Move without HitNear") {
      val trap1 = SharkBlade
      val trap2 = Tombstone
      it("is not legal") {
        assert(Combo.isValidSequence(trap1, trap2) === false)
      }
    }
    describe("Snowball -> SharkBlade") {
      it("is not legal") {
        assert(!Combo.isValidSequence(Snowball, SharkBlade))
      }
    }
  }
}