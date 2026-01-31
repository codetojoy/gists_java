ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "net.codetojoy"
ThisBuild / version := "1.0.0"

// Java settings
ThisBuild / javacOptions ++= Seq("-source", "21", "-target", "21")

lazy val root = (project in file("."))
  .settings(
    name := "sensor-app-foo",

    // Pure Java project
    Compile / compileOrder := CompileOrder.JavaThenScala,
    crossPaths := false,
    autoScalaLibrary := false,

    // Exclude bar package from compilation
    Compile / unmanagedSources / excludeFilter := HiddenFileFilter ||
      new SimpleFileFilter(_.getAbsolutePath.contains("/sensor/bar/")),

    // Exclude bar's encrypted data and services-bar from resources
    Compile / unmanagedResources / excludeFilter := HiddenFileFilter ||
      new SimpleFileFilter(f => f.getName == "sensor_data_encrypted.txt" ||
        f.getAbsolutePath.contains("services-bar")),

    // Main class
    Compile / packageBin / mainClass := Some("net.codetojoy.sensor.common.Runner"),
    assembly / mainClass := Some("net.codetojoy.sensor.common.Runner"),
    assembly / assemblyJarName := "sensor-app-foo.jar",

    // Merge strategy
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "services", _*) => MergeStrategy.concat
      case PathList("META-INF", "versions", _*) => MergeStrategy.first
      case PathList("META-INF", _*) => MergeStrategy.discard
      case _ => MergeStrategy.first
    }
  )
