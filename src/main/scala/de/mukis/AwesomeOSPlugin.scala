package de.mukis

import sbt._
import sbt.Keys.{ streams, target }
import java.nio.charset.Charset

/**
 * This plugin helps you which operating systems are awesome
 */
object AwesomeOSPlugin extends AutoPlugin {

  /**
   * Defines all settings/tasks that get automatically imported,
   * when the plugin is enabled
   */
  object autoImport {
    lazy val awesomeOsPrint = TaskKey[Unit]("awesome-os-print", "Prints all awesome operating systems")
    lazy val awesomeOsStore = TaskKey[File]("awesome-os-store", "Stores all awesome operating systems in a file")
    lazy val awesomeOsList = SettingKey[Seq[String]]("awesome-os-list", "A list of awesome operating systems")
    lazy val awesomeOsFileName = SettingKey[String]("awesome-os-filename", "The filename of awesome os list")
  }

  import autoImport._

  /**
   * Provide default settings
   */
  override lazy val projectSettings = Seq(
    awesomeOsList := Seq(),
    awesomeOsFileName := "awesome-os.txt",
    awesomeOsPrint := print(awesomeOsList.value, streams.value.log),
    awesomeOsStore := store(awesomeOsList.value, target.value / awesomeOsFileName.value)
  )

  /**
   * print all operating systems to the logging output
   *
   * @param systems - list of operating systems
   * @param log - logging output
   */
  def print(systems: Seq[String], log: Logger) {
    systems foreach (os => log info os)
  }

  /**
   * stores the given list of operating systems
   *
   * @param systems - list of operating systems
   * @param output - target where to store the list
   */
  def store(systems: Seq[String], output: File): File = {
    val content = systems mkString "\n"
    IO.write(output, content, Charset forName "UTF-8")
    output
  }

}