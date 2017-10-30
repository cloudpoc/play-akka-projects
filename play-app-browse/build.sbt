name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.json" % "json" % "20090211"
libraryDependencies ++= Seq(
  // ... other libs
  "com.typesafe.akka" %% "akka-remote" % "2.4.14"
)
