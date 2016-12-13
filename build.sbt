scalaVersion := "2.11.8"

organization := "org.lucaster"

name := "ark-calc"

version := "1.0-SNAPSHOT"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
libraryDependencies += "io.reactivex" %% "rxscala" % "0.26.4"
libraryDependencies += "org.optaplanner" % "optaplanner-core" % "6.5.0.Final"

javaHome := sys.env.get("JAVA_HOME") map file

// Do not append Scala versions to the generated artifacts
//crossPaths := false

// This forbids including Scala related libraries into the dependency
//autoScalaLibrary := false