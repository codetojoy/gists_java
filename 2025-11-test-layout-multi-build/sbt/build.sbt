name := "person-example"

version := "0.1.0"

scalaVersion := "2.13.12"

javacOptions ++= Seq("-source", "21", "-target", "21")

// Configure test directories
Test / javaSource := baseDirectory.value / "test" / "acceptance"

// Add JUnit 4 dependencies
libraryDependencies += "junit" % "junit" % "4.13.2" % Test
libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % Test

// Ensure tests run with JUnit
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
