import com.earldouglas.xsbtwebplugin.WebPlugin._
import sbt._
import Keys._

object LXAPIBuild extends Build {

  //generate a conf file for the version
  VersionHelper.BuildVersionsPlugin.buildVersionFile("src/main/resources/version.conf")

  lazy val project = Project (
    "sampleAPI",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(webSettings :_*)++ Seq(
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % "2.2.2",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,compile",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016",
        "ch.qos.logback" % "logback-classic" % "1.0.1",
        "org.scalatra" %% "scalatra-scalatest" % "2.2.2" % "test"
      )
    )
  )
}
