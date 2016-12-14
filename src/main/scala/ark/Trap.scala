package ark

import scala.math.BigDecimal.double2bigDecimal

import TrapAlign._
import TrapEffect._
import TrapType._

sealed case class Trap(val damage: Int,
                       val multiplier: BigDecimal,
                       val kind: TrapType,
                       val align: TrapAlign,
                       val points: Int,
                       val effects: Set[TrapEffect] = Set.empty) {

  def movesVictim = effects.filter { _.isInstanceOf[Move] }.size > 0
  def explodes = effects.contains(Explode)
  def rolls = effects.contains(Roll)
  def isProjectile = effects.contains(Projectile)
}

object Trap {
  val AcidSlime = Trap(15, 1.6, Ceiling, Humiliating, 60, Set(Bind, ForcedArmorBreak))
  val AgonyMask = Trap(45, 0.9, Ceiling, Sadistic, 95, Set(Move2, Electric))
  val Aldebaran = Trap(1, 2.0, Ceiling, Sadistic, 60, Set(Berserk))
  val ArrowSlit = Trap(25, 2.5, Wall, Elaborate, 90, Set(Projectile))
  val BalanceBeam = Trap(3, 1.8, Floor, Humiliating, 70, Set(Bind))
  val BallSpiker = Trap(5, 1.6, Wall, Elaborate, 60, Set(Move1, StageHit3))
  val BananaPeel = Trap(5, 1.3, Floor, Humiliating, 100, Set(Move1))
  val BearTrap = Trap(15, 0.8, Floor, Elaborate, 65, Set(Bind))
  val BidetToilet = Trap(20, 0.6, Floor, Humiliating, 75, Set(Bind, Move2))
  val BlackHole = Trap(50, 0.4, Floor, Humiliating, 50, Set(Bind)) // Move?
  val BlastBomb = Trap(50, 1.2, Floor, Elaborate, 90, Set(Fire, Move2, Explode))
  val BloodyScissors = Trap(50, 0.6, Floor, Sadistic, 80, Set(Bind))
  val Boulder = Trap(50, 0.9, Ceiling, Elaborate, 70, Set(Roll))
  val BrutalBuzzsaw = Trap(30, 1.0, Wall, Sadistic, 65, Set(Projectile, MoveToWall))
  val Bubblehead = Trap(5, 1.1, Floor, Elaborate, 55, Set(Bind, Movable))
  val Buzzsaw = Trap(35, 0.8, Ceiling, Sadistic, 50, Set(Move2))
  val CageBall = Trap(10, 1.2, Ceiling, Elaborate, 90, Set(Roll, Move1))
  val Cakeintheface = Trap(1, 2.0, Wall, Humiliating, 95, Set(Projectile, Move3))
  val ChangingRoom = Trap(5, 1.7, Wall, Humiliating, 55, Set(ForcedArmorBreak))
  val ChurchBell = Trap(50, 1.5, Ceiling, Elaborate, 75)
  val CircularSaw = Trap(10, 0.5, Ceiling, Sadistic, 75, Set(StageHit4, Move2))
  val Claw = Trap(15, 1.7, Ceiling, Elaborate, 58, Set(Bind, Move1))
  val DeadlyFan = Trap(45, 0.4, Wall, Sadistic, 85, Set(Bind)) // Move?
  val DeltaHorse = Trap(30, 0.6, Floor, Humiliating, 85, Set(Bind))
  val DeltaHorseRodeo = Trap(35, 0.5, Floor, Humiliating, 90, Set(Bind, Move3))
  val EvilPunch = Trap(20, 1.0, Floor, Elaborate, 66, Set(StageHit2)) // Move?
  val EvilSmash = Trap(40, 1.0, Wall, Elaborate, 66, Set(MoveToWall))
  val EvilStomp = Trap(50, 1.0, Floor, Humiliating, 66, Set(HitsNear))
  val EvilStrike = Trap(35, 0.8, Floor, Elaborate, 66, Set(MoveToWall))
  val Fireball = Trap(35, 1.8, Wall, Elaborate, 85, Set(Projectile, Fire))
  val FlamingBoulder = Trap(65, 0.7, Ceiling, Elaborate, 75, Set(Roll, Fire))
  val Flypaper = Trap(15, 0.8, Floor, Humiliating, 85, Set(Bind))
  val FlySwatter = Trap(35, 1.2, Wall, Humiliating, 75)
  val FountainToilet = Trap(35, 1.1, Floor, Humiliating, 90, Set(Bind))
  val FrozenClaw = Trap(10, 0.4, Floor, Elaborate, 55, Set(Bind, Ice))
  val FrozenArrow = Trap(20, 2.0, Wall, Elaborate, 75, Set(Projectile, Ice))
  val GatlingArrow = Trap(12, 1.0, Wall, Sadistic, 55, Set(Projectile, StageHit5))
  val GenocideEye = Trap(10, 1.3, Ceiling, Elaborate, 65, Set(Move3))
  val HangingChains = Trap(15, 0.7, Floor, Elaborate, 60, Set(Bind))
  val Hellfire = Trap(65, 1.0, Floor, Sadistic, 75, Set(Fire, Move3, Explode))
  val HellsJudjement = Trap(55, 1.0, Ceiling, Sadistic, 100, Set(Move3, StageHit2, Electric, Explode))
  val HellLaser = Trap(35, 1.1, Wall, Sadistic, 65, Set(Projectile, Electric))
  val HorseHead = Trap(3, 1.4, Ceiling, Humiliating, 80, Set(Move8))
  val Hotplate = Trap(40, 0.7, Floor, Humiliating, 85, Set(Bind, Move2, Fire))
  val HumanCannon = Trap(30, 0.7, Floor, Humiliating, 80, Set(MoveToWall))
  val InverseSaltire = Trap(35, 1.2, Wall, Sadistic, 70, Set(Move2))
  val IronBall = Trap(60, 0.8, Ceiling, Elaborate, 80, Set(Roll, Move4))
  val IronRake = Trap(10, 0.7, Floor, Humiliating, 75, Set(Move1, Enraging))
  val Launchpad = Trap(25, 1.1, Floor, Elaborate, 80, Set(StageHit2))
  val LethalLance = Trap(40, 0.5, Wall, Sadistic, 55, Set(Move1))
  val LightningSpear = Trap(45, 1.4, Wall, Elaborate, 95, Set(Projectile, Electric))
  val KillerTop = Trap(40, 0.9, Ceiling, Sadistic, 80, Set(Enveloping, Move3))
  val MagnifyingGlass = Trap(40, 1.8, Ceiling, Elaborate, 110, Set(Fire))
  val MaidensEmbrace = Trap(60, 0.8, Wall, Sadistic, 100)
  val Mallet = Trap(7, 1.3, Wall, Humiliating, 70, Set(Slide1))
  val MegaYoYo = Trap(30, 0.6, Ceiling, Humiliating, 50, Set(Move3))
  val MovingChair = Trap(15, 1.3, Wall, Humiliating, 60, Set(MoveToWall))
  val NastyNeedle = Trap(55, 1.0, Ceiling, Sadistic, 70)
  val NerveGas = Trap(3, 0.7, Wall, Elaborate, 50, Set(Slow, Flammable))
  val NightmareHeel = Trap(65, 1.3, Ceiling, Sadistic, 65)
  val OilBottle = Trap(3, 1.0, Ceiling, Elaborate, 65, Set(Move4, Oil))
  val OilPress = Trap(5, 1.4, Wall, Humiliating, 65, Set(Oil, Flammable, HitsNear))
  val PaperFan = Trap(8, 0.9, Wall, Humiliating, 100, Set(Slide2))
  val Pillory = Trap(10, 0.8, Floor, Humiliating, 95, Set(Bind))
  val PumpkinMask = Trap(1, 1.0, Ceiling, Humiliating, 65, Set(Move4))
  val RailHook = Trap(45, 0.6, Ceiling, Sadistic, 85, Set(MoveToWall))
  val ReapersScythe = Trap(50, 0.9, Wall, Sadistic, 70, Set(Move2))
  val RodeoHorseJet = Trap(45, 0.4, Floor, Sadistic, 90, Set(MoveToWall))
  val RollingBomb = Trap(55, 1.2, Wall, Elaborate, 100, Set(Roll, Move2, Fire))
  val ScreamFace = Trap(5, 1.9, Ceiling, Humiliating, 60, Set(Move3))
  val SharkBlade = Trap(25, 1.0, Floor, Sadistic, 50)
  val Skyrocket = Trap(20, 0.9, Floor, Elaborate, 75, Set(Move3, Fire, StageHit2))
  val Snowball = Trap(20, 1.1, Ceiling, Humiliating, 55, Set(Roll, Enveloping))
  val SparkRod = Trap(35, 0.6, Floor, Elaborate, 60, Set(Bind, Electric))
  val Spatula = Trap(15, 1.2, Wall, Elaborate, 60, Set(StageHit2))
  val SpikedBall = Trap(70, 0.7, Ceiling, Sadistic, 90, Set(Roll, MoveToWall, Enveloping))
  val Springboard = Trap(5, 0.7, Floor, Elaborate, 50, Set(Move3))
  val StickyArrow = Trap(3, 0.7, Wall, Humiliating, 70, Set(Projectile, MoveToWall))
  val StrayTrapDog = Trap(30, 0.8, Floor, Humiliating, 60, Set(Move3))
  val Suezo = Trap(70, 1.0, Ceiling, Humiliating, 50, Set(Roll, Move5))
  val SuperSpringboard = Trap(10, 0.8, Floor, Elaborate, 55, Set(Move6))
  val SuspendedCeiling = Trap(45, 1.2, Ceiling, Humiliating, 60, Set(HitsNear, StageHit2))
  val SwingingAnchor = Trap(35, 0.6, Ceiling, Sadistic, 80, Set(Enveloping))
  val SwingingAxe = Trap(45, 0.9, Ceiling, Sadistic, 60, Set(Move3))
  val SwingingHammer = Trap(20, 0.9, Ceiling, Elaborate, 55, Set(Move4))
  val Syringe = Trap(20, 1.4, Wall, Elaborate, 70, Set(Projectile, Slow, Move1))
  val Tombstone = Trap(44, 0.9, Ceiling, Humiliating, 60)
  val TortureWheel = Trap(30, 0.8, Ceiling, Sadistic, 70, Set(Enveloping))
  val TrapDog = Trap(30, 0.7, Floor, Sadistic, 50, Set(Bind))
  val VacuumFloor = Trap(1, 0.5, Floor, Elaborate, 50, Set(Bind))
  val VacuumWall = Trap(1, 0.6, Wall, Elaborate, 55, Set(Move2, Bind))
  val Vase = Trap(1, 1.0, Ceiling, Elaborate, 70, Set(Move4))
  val VaultingBoxSpringboard = Trap(5, 1.3, Floor, Elaborate, 60, Set(Move1))
  val ViceGrip = Trap(10, 0.9, Floor, Humiliating, 65, Set(Bind, Move2))
  val WallNudge = Trap(5, 1.2, Wall, Elaborate, 65, Set(Move2))
  val WallSmash = Trap(15, 1.4, Wall, Elaborate, 70, Set(Move6))
  val Washbin = Trap(7, 2.4, Ceiling, Humiliating, 90, Set(Move1, Enraging))
  val WonderBalloon = Trap(10, 0.8, Floor, Humiliating, 65, Set(Bind))

  val values = Set(
    AcidSlime,
    AgonyMask,
    Aldebaran,
    ArrowSlit,
    BalanceBeam,
    BallSpiker,
    BananaPeel,
    BearTrap,
    BidetToilet,
    BlackHole,
    BlastBomb,
    BloodyScissors,
    Boulder,
    BrutalBuzzsaw,
    Bubblehead,
    Buzzsaw,
    CageBall,
    Cakeintheface,
    ChangingRoom,
    ChurchBell,
    CircularSaw,
    Claw,
    DeadlyFan,
    DeltaHorse,
    DeltaHorseRodeo,
    EvilPunch,
    EvilSmash,
    EvilStomp,
    EvilStrike,
    Fireball,
    FlamingBoulder,
    Flypaper,
    FlySwatter,
    FountainToilet,
    FrozenClaw,
    FrozenArrow,
    GatlingArrow,
    GenocideEye,
    HangingChains,
    Hellfire,
    HellsJudjement,
    HellLaser,
    HorseHead,
    Hotplate,
    HumanCannon,
    InverseSaltire,
    IronBall,
    IronRake,
    Launchpad,
    LethalLance,
    LightningSpear,
    KillerTop,
    MagnifyingGlass,
    MaidensEmbrace,
    Mallet,
    MegaYoYo,
    MovingChair,
    NastyNeedle,
    NerveGas,
    NightmareHeel,
    OilBottle,
    OilPress,
    PaperFan,
    Pillory,
    PumpkinMask,
    RailHook,
    ReapersScythe,
    RodeoHorseJet,
    RollingBomb,
    ScreamFace,
    SharkBlade,
    Skyrocket,
    Snowball,
    SparkRod,
    Spatula,
    SpikedBall,
    Springboard,
    StickyArrow,
    StrayTrapDog,
    Suezo,
    SuperSpringboard,
    SuspendedCeiling,
    SwingingAnchor,
    SwingingAxe,
    SwingingHammer,
    Syringe,
    Tombstone,
    TortureWheel,
    TrapDog,
    VacuumFloor,
    VacuumWall,
    Vase,
    VaultingBoxSpringboard,
    ViceGrip,
    WallNudge,
    WallSmash,
    Washbin,
    WonderBalloon)
}