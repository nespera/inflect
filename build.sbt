name := "inflect"

version := "0.2.0"

scalaVersion := "2.10.2"

crossScalaVersions := Seq("2.8.2", "2.9.3", "2.10.2")

libraryDependencies <+= scalaVersion {
  case sv if sv startsWith "2.8" => "org.scalatest" %% "scalatest" % "1.8" % "test"
  case _ => "org.scalatest" %% "scalatest" % "1.9.1" % "test"
}

unmanagedSourceDirectories in Compile <+= (sourceDirectory in Compile, scalaVersion){ (s,v) => s / ("scala-"+v) }

libraryDependencies += "com.novocode" % "junit-interface" % "0.10-M4" % "test"

libraryDependencies += "junit" % "junit-dep" % "4.10"

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.3"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
