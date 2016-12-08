package ark

import TrapAlign._
import TrapEffect._
import TrapType._
import javafx.scene.shape.MoveTo

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

object Trap {

  case object AcidSlime extends TrapSkeleton(15, 1.6, Ceiling, Humiliating, 60, Set(Bind, ForcedArmorBreak))
  case object AgonyMask extends TrapSkeleton(45, 0.9, Ceiling, Sadistic, 95, Set(Move2, Electric))
  case object Aldebaran extends TrapSkeleton(1, 2.0, Ceiling, Sadistic, 60, Set(Berserk))
  case object ArrowSlit extends TrapSkeleton(25, 2.5, Wall, Elaborate, 90, Set(Projectile))
  case object BalanceBeam extends TrapSkeleton(3, 1.8, Floor, Humiliating, 70, Set(Bind))
  case object BallSpiker extends TrapSkeleton(5, 1.6, Wall, Elaborate, 60, Set(Move1, StageHit3))
  case object BananaPeel extends TrapSkeleton(5, 1.3, Floor, Humiliating, 100, Set(Move1))
  case object BearTrap extends TrapSkeleton(15, 0.8, Floor, Elaborate, 65, Set(Bind))
  case object BidetToilet extends TrapSkeleton(20, 0.6, Floor, Humiliating, 75, Set(Bind, Move2))
  case object BlackHole extends TrapSkeleton(50, 0.4, Floor, Humiliating, 50, Set(Bind)) // Move?
  case object BlastBomb extends TrapSkeleton(50, 1.2, Floor, Elaborate, 90, Set(Fire, Move2, Explode))
  case object BloodyScissors extends TrapSkeleton(50, 0.6, Floor, Sadistic, 80, Set(Bind))
  case object Boulder extends TrapSkeleton(50, 0.9, Ceiling, Elaborate, 70, Set(Roll))
  case object BrutalBuzzsaw extends TrapSkeleton(30, 1.0, Wall, Sadistic, 65, Set(Projectile, MoveToWall))
  case object Bubblehead extends TrapSkeleton(5, 1.1, Floor, Elaborate, 55, Set(Bind, Movable))
  case object Buzzsaw extends TrapSkeleton(35, 0.8, Ceiling, Sadistic, 50, Set(Move2))
  case object CageBall extends TrapSkeleton(10, 1.2, Ceiling, Elaborate, 90, Set(Roll, Move1))
  case object Cakeintheface extends TrapSkeleton(1, 2.0, Wall, Humiliating, 95, Set(Projectile, Move3))
  case object ChangingRoom extends TrapSkeleton(5, 1.7, Wall, Humiliating, 55, Set(ForcedArmorBreak))
  case object ChurchBell extends TrapSkeleton(50, 1.5, Ceiling, Elaborate, 75)
  case object CircularSaw extends TrapSkeleton(10, 0.5, Ceiling, Sadistic, 75, Set(StageHit4, Move2))
  case object Claw extends TrapSkeleton(15, 1.7, Ceiling, Elaborate, 58, Set(Bind, Move1))
  case object DeadlyFan extends TrapSkeleton(45, 0.4, Wall, Sadistic, 85, Set(Bind)) // Move?
  case object DeltaHorse extends TrapSkeleton(30, 0.6, Floor, Humiliating, 85, Set(Bind))
  case object DeltaHorseRodeo extends TrapSkeleton(35, 0.5, Floor, Humiliating, 90, Set(Bind, Move3))
  case object EvilPunch extends TrapSkeleton(20, 1.0, Floor, Elaborate, 66, Set(StageHit2)) // Move?
  case object EvilSmash extends TrapSkeleton(40, 1.0, Wall, Elaborate, 66, Set(MoveToWall))
  case object EvilStomp extends TrapSkeleton(50, 1.0, Floor, Humiliating, 66, Set(HitsNear))
  case object EvilStrike extends TrapSkeleton(35, 0.8, Floor, Elaborate, 66, Set(MoveToWall))
  case object Fireball extends TrapSkeleton(35, 1.8, Wall, Elaborate, 85, Set(Projectile, Fire))
  case object FlamingBoulder extends TrapSkeleton(65, 0.7, Ceiling, Elaborate, 75, Set(Roll, Fire))
  case object Flypaper extends TrapSkeleton(15, 0.8, Floor, Humiliating, 85, Set(Bind))
  case object FlySwatter extends TrapSkeleton(35, 1.2, Wall, Humiliating, 75)
  case object FountainToilet extends TrapSkeleton(35, 1.1, Floor, Humiliating, 90, Set(Bind))
  case object FrozenClaw extends TrapSkeleton(10, 0.4, Floor, Elaborate, 55, Set(Bind, Ice))
  case object FrozenArrow extends TrapSkeleton(20, 2.0, Wall, Elaborate, 75, Set(Projectile, Ice))
  case object GatlingArrow extends TrapSkeleton(12, 1.0, Wall, Sadistic, 55, Set(Projectile, StageHit5))
  case object GenocideEye extends TrapSkeleton(10, 1.3, Ceiling, Elaborate, 65, Set(Move3))
  case object HangingChains extends TrapSkeleton(15, 0.7, Floor, Elaborate, 60, Set(Bind))
  case object Hellfire extends TrapSkeleton(65, 1.0, Floor, Sadistic, 75, Set(Fire, Move3, Explode))
  case object HellsJudjement extends TrapSkeleton(55, 1.0, Ceiling, Sadistic, 100, Set(Move3, StageHit2, Electric, Explode))
  case object HellLaser extends TrapSkeleton(35, 1.1, Wall, Sadistic, 65, Set(Projectile, Electric))
  case object HorseHead extends TrapSkeleton(3, 1.4, Ceiling, Humiliating, 80, Set(Move8))
  case object Hotplate extends TrapSkeleton(40, 0.7, Floor, Humiliating, 85, Set(Bind, Move2, Fire))
  case object HumanCannon extends TrapSkeleton(30, 0.7, Floor, Humiliating, 80, Set(MoveToWall))
  case object InverseSaltire extends TrapSkeleton(35, 1.2, Wall, Sadistic, 70, Set(Move2))
  case object IronBall extends TrapSkeleton(60, 0.8, Ceiling, Elaborate, 80, Set(Roll, Move4))
  case object IronRake extends TrapSkeleton(10, 0.7, Floor, Humiliating, 75, Set(Move1, Enraging))
  case object Launchpad extends TrapSkeleton(25, 1.1, Floor, Elaborate, 80, Set(StageHit2))
  case object LethalLance extends TrapSkeleton(40, 0.5, Wall, Sadistic, 55, Set(Move1))
  case object LightningSpear extends TrapSkeleton(45, 1.4, Wall, Elaborate, 95, Set(Projectile, Electric))
  case object KillerTop extends TrapSkeleton(40, 0.9, Ceiling, Sadistic, 80, Set(Enveloping, Move3))
  case object MagnifyingGlass extends TrapSkeleton(40, 1.8, Ceiling, Elaborate, 110, Set(Fire))
  case object MaidensEmbrace extends TrapSkeleton(60, 0.8, Wall, Sadistic, 100)
  case object Mallet extends TrapSkeleton(7, 1.3, Wall, Humiliating, 70, Set(Slide1))
  case object MegaYoYo extends TrapSkeleton(30, 0.6, Ceiling, Humiliating, 50, Set(Move3))
  case object MovingChair extends TrapSkeleton(15, 1.3, Wall, Humiliating, 60, Set(MoveToWall))
  case object NastyNeedle extends TrapSkeleton(55, 1.0, Ceiling, Sadistic, 70)
  case object NerveGas extends TrapSkeleton(3, 0.7, Wall, Elaborate, 50, Set(Slow, Flammable))
  case object NightmareHeel extends TrapSkeleton(65, 1.3, Ceiling, Sadistic, 65)
  case object OilBottle extends TrapSkeleton(3, 1.0, Ceiling, Elaborate, 65, Set(Move4, Oil))
  case object OilPress extends TrapSkeleton(5, 1.4, Wall, Humiliating, 65, Set(Oil, Flammable, HitsNear))
  case object PaperFan extends TrapSkeleton(8, 0.9, Wall, Humiliating, 100, Set(Slide2))
  case object Pillory extends TrapSkeleton(10, 0.8, Floor, Humiliating, 95, Set(Bind))
  case object PumpkinMask extends TrapSkeleton(1, 1.0, Ceiling, Humiliating, 65, Set(Move4))
  case object RailHook extends TrapSkeleton(45, 0.6, Ceiling, Sadistic, 85, Set(MoveToWall))
  case object ReapersScythe extends TrapSkeleton(50, 0.9, Wall, Sadistic, 70, Set(Move2))
  case object RodeoHorseJet extends TrapSkeleton(45, 0.4, Floor, Sadistic, 90, Set(MoveToWall))
  case object RollingBomb extends TrapSkeleton(55, 1.2, Wall, Elaborate, 100, Set(Roll, Move2, Fire))
  case object ScreamFace extends TrapSkeleton(5, 1.9, Ceiling, Humiliating, 60, Set(Move3))
  case object SharkBlade extends TrapSkeleton(25, 1.0, Floor, Sadistic, 50)
  case object Skyrocket extends TrapSkeleton(20, 0.9, Floor, Elaborate, 75, Set(Move3, Fire, StageHit2))
  case object Snowball extends TrapSkeleton(20, 1.1, Ceiling, Humiliating, 55, Set(Roll, Enveloping))
  case object SparkRod extends TrapSkeleton(35, 0.6, Floor, Elaborate, 60, Set(Bind, Electric))
  case object Spatula extends TrapSkeleton(15, 1.2, Wall, Elaborate, 60, Set(StageHit2))
  case object SpikedBall extends TrapSkeleton(70, 0.7, Ceiling, Sadistic, 90, Set(Roll, MoveToWall, Enveloping))
  case object Springboard extends TrapSkeleton(5, 0.7, Floor, Elaborate, 50, Set(Move3))
  case object StickyArrow extends TrapSkeleton(3, 0.7, Wall, Humiliating, 70, Set(Projectile, MoveToWall))
  case object StrayTrapDog extends TrapSkeleton(30, 0.8, Floor, Humiliating, 60, Set(Move3))
  case object Suezo extends TrapSkeleton(70, 1.0, Ceiling, Humiliating, 50, Set(Roll, Move5))
  case object SuperSpringboard extends TrapSkeleton(10, 0.8, Floor, Elaborate, 55, Set(Move6))
  case object SuspendedCeiling extends TrapSkeleton(45, 1.2, Ceiling, Humiliating, 60, Set(HitsNear, StageHit2))
  case object SwingingAnchor extends TrapSkeleton(35, 0.6, Ceiling, Sadistic, 80, Set(Enveloping))
  case object SwingingAxe extends TrapSkeleton(45, 0.9, Ceiling, Sadistic, 60, Set(Move3))
  case object SwingingHammer extends TrapSkeleton(20, 0.9, Ceiling, Elaborate, 55, Set(Move4))
  case object Syringe extends TrapSkeleton(20, 1.4, Wall, Elaborate, 70, Set(Projectile, Slow, Move1))
  case object Tombstone extends TrapSkeleton(44, 0.9, Ceiling, Humiliating, 60)
  case object TortureWheel extends TrapSkeleton(30, 0.8, Ceiling, Sadistic, 70, Set(Enveloping))
  case object TrapDog extends TrapSkeleton(30, 0.7, Floor, Sadistic, 50, Set(Bind))
  case object VacuumFloor extends TrapSkeleton(1, 0.5, Floor, Elaborate, 50, Set(Bind))
  case object VacuumWall extends TrapSkeleton(1, 0.6, Wall, Elaborate, 55, Set(Move2, Bind))
  case object Vase extends TrapSkeleton(1, 1.0, Ceiling, Elaborate, 70, Set(Move4))
  case object VaultingBoxSpringboard extends TrapSkeleton(5, 1.3, Floor, Elaborate, 60, Set(Move1))
  case object ViceGrip extends TrapSkeleton(10, 0.9, Floor, Humiliating, 65, Set(Bind, Move2))
  case object WallNudge extends TrapSkeleton(5, 1.2, Wall, Elaborate, 65, Set(Move2))
  case object WallSmash extends TrapSkeleton(15, 1.4, Wall, Elaborate, 70, Set(Move6))
  case object Washbin extends TrapSkeleton(7, 2.4, Ceiling, Humiliating, 90, Set(Move1, Enraging))
  case object WonderBalloon extends TrapSkeleton(10, 0.8, Floor, Humiliating, 65, Set(Bind))

  sealed abstract class TrapSkeleton(val damage: Int,
                                     val multiplier: BigDecimal,
                                     val kind: TrapType,
                                     val align: TrapAlign,
                                     val points: Int,
                                     val effects: Set[TrapEffect] = Set.empty) extends Trap {
  }

  val values = Set(
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
    EvilStomp,
    EvilStrike,
    Fireball,
    FlamingBoulder,
    Flypaper,
    FountainToilet,
    FrozenArrow,
    GatlingArrow,
    HangingChains,
    Hellfire,
    HorseHead,
    Hotplate,
    HumanCannon,
    InverseSaltire,
    IronBall,
    IronRake,
    Launchpad,
    LethalLance,
    LightningSpear,
    MagnifyingGlass,
    MaidensEmbrace,
    Mallet,
    MegaYoYo,
    MovingChair,
    NastyNeedle,
    NerveGas,
    OilBottle,
    Pillory,
    PumpkinMask,
    ReapersScythe,
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
    SuperSpringboard,
    SwingingAnchor,
    SwingingAxe,
    SwingingHammer,
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
    WonderBalloon);
}