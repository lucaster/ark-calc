package ark

import scala.math.BigDecimal.double2bigDecimal

import TrapAlign._
import TrapEffect._
import TrapType._

/**
 * Not trait's object companion, better for Java interop:
 * + Works almost like an enum. Es: Traps.AcidSlime()
 * + Because values aren't case objects, they don't turn into (undesirably) instantiable classes and are immutable final
 * - The type of values is the trait, not the enclosing object
 */
object Traps {
  val AcidSlime = TrapSkeleton(15, 1.6, Ceiling, Humiliating, 60, Set(Bind, ForcedArmorBreak))
  val AgonyMask = TrapSkeleton(45, 0.9, Ceiling, Sadistic, 95, Set(Move2, Electric))
  val Aldebaran = TrapSkeleton(1, 2.0, Ceiling, Sadistic, 60, Set(Berserk))
  val ArrowSlit = TrapSkeleton(25, 2.5, Wall, Elaborate, 90, Set(Projectile))
  val BalanceBeam = TrapSkeleton(3, 1.8, Floor, Humiliating, 70, Set(Bind))
  val BallSpiker = TrapSkeleton(5, 1.6, Wall, Elaborate, 60, Set(Move1, StageHit3))
  val BananaPeel = TrapSkeleton(5, 1.3, Floor, Humiliating, 100, Set(Move1))
  val BearTrap = TrapSkeleton(15, 0.8, Floor, Elaborate, 65, Set(Bind))
  val BidetToilet = TrapSkeleton(20, 0.6, Floor, Humiliating, 75, Set(Bind, Move2))
  val BlackHole = TrapSkeleton(50, 0.4, Floor, Humiliating, 50, Set(Bind)) // Move?
  val BlastBomb = TrapSkeleton(50, 1.2, Floor, Elaborate, 90, Set(Fire, Move2, Explode))
  val BloodyScissors = TrapSkeleton(50, 0.6, Floor, Sadistic, 80, Set(Bind))
  val Boulder = TrapSkeleton(50, 0.9, Ceiling, Elaborate, 70, Set(Roll))
  val BrutalBuzzsaw = TrapSkeleton(30, 1.0, Wall, Sadistic, 65, Set(Projectile, MoveToWall))
  val Bubblehead = TrapSkeleton(5, 1.1, Floor, Elaborate, 55, Set(Bind, Movable))
  val Buzzsaw = TrapSkeleton(35, 0.8, Ceiling, Sadistic, 50, Set(Move2))
  val CageBall = TrapSkeleton(10, 1.2, Ceiling, Elaborate, 90, Set(Roll, Move1))
  val Cakeintheface = TrapSkeleton(1, 2.0, Wall, Humiliating, 95, Set(Projectile, Move3))
  val ChangingRoom = TrapSkeleton(5, 1.7, Wall, Humiliating, 55, Set(ForcedArmorBreak))
  val ChurchBell = TrapSkeleton(50, 1.5, Ceiling, Elaborate, 75)
  val CircularSaw = TrapSkeleton(10, 0.5, Ceiling, Sadistic, 75, Set(StageHit4, Move2))
  val Claw = TrapSkeleton(15, 1.7, Ceiling, Elaborate, 58, Set(Bind, Move1))
  val DeadlyFan = TrapSkeleton(45, 0.4, Wall, Sadistic, 85, Set(Bind)) // Move?
  val DeltaHorse = TrapSkeleton(30, 0.6, Floor, Humiliating, 85, Set(Bind))
  val DeltaHorseRodeo = TrapSkeleton(35, 0.5, Floor, Humiliating, 90, Set(Bind, Move3))
  val EvilPunch = TrapSkeleton(20, 1.0, Floor, Elaborate, 66, Set(StageHit2)) // Move?
  val EvilSmash = TrapSkeleton(40, 1.0, Wall, Elaborate, 66, Set(MoveToWall))
  val EvilStomp = TrapSkeleton(50, 1.0, Floor, Humiliating, 66, Set(HitsNear))
  val EvilStrike = TrapSkeleton(35, 0.8, Floor, Elaborate, 66, Set(MoveToWall))
  val Fireball = TrapSkeleton(35, 1.8, Wall, Elaborate, 85, Set(Projectile, Fire))
  val FlamingBoulder = TrapSkeleton(65, 0.7, Ceiling, Elaborate, 75, Set(Roll, Fire))
  val Flypaper = TrapSkeleton(15, 0.8, Floor, Humiliating, 85, Set(Bind))
  val FlySwatter = TrapSkeleton(35, 1.2, Wall, Humiliating, 75)
  val FountainToilet = TrapSkeleton(35, 1.1, Floor, Humiliating, 90, Set(Bind))
  val FrozenClaw = TrapSkeleton(10, 0.4, Floor, Elaborate, 55, Set(Bind, Ice))
  val FrozenArrow = TrapSkeleton(20, 2.0, Wall, Elaborate, 75, Set(Projectile, Ice))
  val GatlingArrow = TrapSkeleton(12, 1.0, Wall, Sadistic, 55, Set(Projectile, StageHit5))
  val GenocideEye = TrapSkeleton(10, 1.3, Ceiling, Elaborate, 65, Set(Move3))
  val HangingChains = TrapSkeleton(15, 0.7, Floor, Elaborate, 60, Set(Bind))
  val Hellfire = TrapSkeleton(65, 1.0, Floor, Sadistic, 75, Set(Fire, Move3, Explode))
  val HellsJudjement = TrapSkeleton(55, 1.0, Ceiling, Sadistic, 100, Set(Move3, StageHit2, Electric, Explode))
  val HellLaser = TrapSkeleton(35, 1.1, Wall, Sadistic, 65, Set(Projectile, Electric))
  val HorseHead = TrapSkeleton(3, 1.4, Ceiling, Humiliating, 80, Set(Move8))
  val Hotplate = TrapSkeleton(40, 0.7, Floor, Humiliating, 85, Set(Bind, Move2, Fire))
  val HumanCannon = TrapSkeleton(30, 0.7, Floor, Humiliating, 80, Set(MoveToWall))
  val InverseSaltire = TrapSkeleton(35, 1.2, Wall, Sadistic, 70, Set(Move2))
  val IronBall = TrapSkeleton(60, 0.8, Ceiling, Elaborate, 80, Set(Roll, Move4))
  val IronRake = TrapSkeleton(10, 0.7, Floor, Humiliating, 75, Set(Move1, Enraging))
  val Launchpad = TrapSkeleton(25, 1.1, Floor, Elaborate, 80, Set(StageHit2))
  val LethalLance = TrapSkeleton(40, 0.5, Wall, Sadistic, 55, Set(Move1))
  val LightningSpear = TrapSkeleton(45, 1.4, Wall, Elaborate, 95, Set(Projectile, Electric))
  val KillerTop = TrapSkeleton(40, 0.9, Ceiling, Sadistic, 80, Set(Enveloping, Move3))
  val MagnifyingGlass = TrapSkeleton(40, 1.8, Ceiling, Elaborate, 110, Set(Fire))
  val MaidensEmbrace = TrapSkeleton(60, 0.8, Wall, Sadistic, 100)
  val Mallet = TrapSkeleton(7, 1.3, Wall, Humiliating, 70, Set(Slide1))
  val MegaYoYo = TrapSkeleton(30, 0.6, Ceiling, Humiliating, 50, Set(Move3))
  val MovingChair = TrapSkeleton(15, 1.3, Wall, Humiliating, 60, Set(MoveToWall))
  val NastyNeedle = TrapSkeleton(55, 1.0, Ceiling, Sadistic, 70)
  val NerveGas = TrapSkeleton(3, 0.7, Wall, Elaborate, 50, Set(Slow, Flammable))
  val NightmareHeel = TrapSkeleton(65, 1.3, Ceiling, Sadistic, 65)
  val OilBottle = TrapSkeleton(3, 1.0, Ceiling, Elaborate, 65, Set(Move4, Oil))
  val OilPress = TrapSkeleton(5, 1.4, Wall, Humiliating, 65, Set(Oil, Flammable, HitsNear))
  val PaperFan = TrapSkeleton(8, 0.9, Wall, Humiliating, 100, Set(Slide2))
  val Pillory = TrapSkeleton(10, 0.8, Floor, Humiliating, 95, Set(Bind))
  val PumpkinMask = TrapSkeleton(1, 1.0, Ceiling, Humiliating, 65, Set(Move4))
  val RailHook = TrapSkeleton(45, 0.6, Ceiling, Sadistic, 85, Set(MoveToWall))
  val ReapersScythe = TrapSkeleton(50, 0.9, Wall, Sadistic, 70, Set(Move2))
  val RodeoHorseJet = TrapSkeleton(45, 0.4, Floor, Sadistic, 90, Set(MoveToWall))
  val RollingBomb = TrapSkeleton(55, 1.2, Wall, Elaborate, 100, Set(Roll, Move2, Fire))
  val ScreamFace = TrapSkeleton(5, 1.9, Ceiling, Humiliating, 60, Set(Move3))
  val SharkBlade = TrapSkeleton(25, 1.0, Floor, Sadistic, 50)
  val Skyrocket = TrapSkeleton(20, 0.9, Floor, Elaborate, 75, Set(Move3, Fire, StageHit2))
  val Snowball = TrapSkeleton(20, 1.1, Ceiling, Humiliating, 55, Set(Roll, Enveloping))
  val SparkRod = TrapSkeleton(35, 0.6, Floor, Elaborate, 60, Set(Bind, Electric))
  val Spatula = TrapSkeleton(15, 1.2, Wall, Elaborate, 60, Set(StageHit2))
  val SpikedBall = TrapSkeleton(70, 0.7, Ceiling, Sadistic, 90, Set(Roll, MoveToWall, Enveloping))
  val Springboard = TrapSkeleton(5, 0.7, Floor, Elaborate, 50, Set(Move3))
  val StickyArrow = TrapSkeleton(3, 0.7, Wall, Humiliating, 70, Set(Projectile, MoveToWall))
  val StrayTrapDog = TrapSkeleton(30, 0.8, Floor, Humiliating, 60, Set(Move3))
  val Suezo = TrapSkeleton(70, 1.0, Ceiling, Humiliating, 50, Set(Roll, Move5))
  val SuperSpringboard = TrapSkeleton(10, 0.8, Floor, Elaborate, 55, Set(Move6))
  val SuspendedCeiling = TrapSkeleton(45, 1.2, Ceiling, Humiliating, 60, Set(HitsNear, StageHit2))
  val SwingingAnchor = TrapSkeleton(35, 0.6, Ceiling, Sadistic, 80, Set(Enveloping))
  val SwingingAxe = TrapSkeleton(45, 0.9, Ceiling, Sadistic, 60, Set(Move3))
  val SwingingHammer = TrapSkeleton(20, 0.9, Ceiling, Elaborate, 55, Set(Move4))
  val Syringe = TrapSkeleton(20, 1.4, Wall, Elaborate, 70, Set(Projectile, Slow, Move1))
  val Tombstone = TrapSkeleton(44, 0.9, Ceiling, Humiliating, 60)
  val TortureWheel = TrapSkeleton(30, 0.8, Ceiling, Sadistic, 70, Set(Enveloping))
  val TrapDog = TrapSkeleton(30, 0.7, Floor, Sadistic, 50, Set(Bind))
  val VacuumFloor = TrapSkeleton(1, 0.5, Floor, Elaborate, 50, Set(Bind))
  val VacuumWall = TrapSkeleton(1, 0.6, Wall, Elaborate, 55, Set(Move2, Bind))
  val Vase = TrapSkeleton(1, 1.0, Ceiling, Elaborate, 70, Set(Move4))
  val VaultingBoxSpringboard = TrapSkeleton(5, 1.3, Floor, Elaborate, 60, Set(Move1))
  val ViceGrip = TrapSkeleton(10, 0.9, Floor, Humiliating, 65, Set(Bind, Move2))
  val WallNudge = TrapSkeleton(5, 1.2, Wall, Elaborate, 65, Set(Move2))
  val WallSmash = TrapSkeleton(15, 1.4, Wall, Elaborate, 70, Set(Move6))
  val Washbin = TrapSkeleton(7, 2.4, Ceiling, Humiliating, 90, Set(Move1, Enraging))
  val WonderBalloon = TrapSkeleton(10, 0.8, Floor, Humiliating, 65, Set(Bind))

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

  protected sealed case class TrapSkeleton(val damage: Int,
                                           val multiplier: BigDecimal,
                                           val kind: TrapType,
                                           val align: TrapAlign,
                                           val points: Int,
                                           val effects: Set[TrapEffect] = Set.empty) extends Trap
}

sealed trait Trap {
  def damage: Int
  def multiplier: BigDecimal
  def kind: TrapType
  def align: TrapAlign
  def points: Int
  def effects: Set[TrapEffect]
  def movesVictim = effects.filter { effect => effect.isInstanceOf[Move] }.size > 0
  def explodes = effects.contains(Explode)
  def rolls = effects.contains(Roll)
  def isProjectile = effects.contains(Projectile)
}