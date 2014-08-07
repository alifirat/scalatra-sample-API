import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

import sbt._

import scala.util.matching.Regex

object VersionHelper {

  import scala.sys.process._

  val projectName = "Sample API"

  val majorVersion = "1"
  val minorVersion = "1"
  val patchVersion = "0"

  //regex for matching git sha
  //the git command will return the sha in single quotes followed by a newline
  private val sha1Matcher = """[0-9a-f]{5,40}""".r

  private val sha = sha1Matcher findFirstIn "git log --pretty=format:'%H' -1".!!
  //!! actually executes the string as a command in the shell
  //the regex will do a greedy match which ignores the single quotes and any newlines

  def gitSha: String = sha match {
    case Some(s) => s
    case None => "0000000000000000000000000000000000000000" //error
  }

  val version = s"$majorVersion.$minorVersion.$patchVersion.$gitSha"

  object BuildVersionsPlugin extends Plugin {

    def buildVersionFile(versionFilePath: String): Seq[File] = {

      //src/main/resources/
      var targetFile = new File(versionFilePath)

      if (targetFile.exists()) {
        targetFile.delete()
      }

      targetFile.createNewFile()

      val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z")
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
      val generated = formatter.format(Calendar.getInstance.getTime)

      val fileWriter = new FileWriter(targetFile)
      fileWriter.write(
        s"""
          | # IMPORTANT! This file is auto-generated. Any changes will be lost
          | # Last generated: $generated

          | build {
          |   name = "$projectName"
          |   version = "$version"
          |   lastBuilt = "$generated"
          | }
        """.stripMargin
      )
      fileWriter.close()
      Seq(targetFile)
    }
  }
}