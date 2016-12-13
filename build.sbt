scalaVersion := "2.11.8"

organization := "org.lucaster"

name := "ark-calc"

version := "1.0-SNAPSHOT"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
libraryDependencies += "io.reactivex" %% "rxscala" % "0.26.4"
libraryDependencies += "org.optaplanner" % "optaplanner-core" % "6.5.0.Final"

//resolvers += "Sonatype Public" at "https://oss.sonatype.org/content/repositories/public"
//resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"