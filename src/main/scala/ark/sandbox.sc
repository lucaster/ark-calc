package ark

object sandbox {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import ark._
  import ark.Trap._

  val sf = new Hit(SpringFloor);                  //> sf  : ark.Hit = Hit(SpringFloor,0.0)
  var pw = new Hit(PushWall);                     //> pw  : ark.Hit = Hit(PushWall,0.0)

  val combo = new Combo(List(sf, pw))             //> combo  : ark.Combo = Combo(List(Hit(SpringFloor,0.0), Hit(PushWall,0.0)))
  
  println(combo.ark);                             //> 22
}