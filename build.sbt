name := "inflect"

version := "0.0.1"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10-M4" % "test"

libraryDependencies += "junit" % "junit-dep" % "4.10"

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.3"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")