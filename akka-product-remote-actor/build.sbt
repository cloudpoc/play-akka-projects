name := "akka-sample-remote-java"

version := "1.0"

scalaVersion := "2.12.2"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2"
libraryDependencies += "org.json" % "json" % "20090211"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "com.typesafe.akka" %% "akka-remote" % "2.5.3"
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))
