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
    awesomeOsList := Seq(
      "Ubuntu 12.04 LTS",
      "Ubuntu 14.04 LTS",
      "Debian Squeeze",
      "Fedora 20",
      "CentOS 6",
      "Android 4.x",
      "Windows 2000",
      "Windows XP",
      "Windows 7",
      "Windows 8.1",
      "MacOS Maverick",
      "MacOS Yosemite",
      "iOS 6",
      "iOS 7"
    ),
    awesomeOsFileName := "awesome-os.txt",
    awesomeOsPrint := {
      awesomeOsList.value foreach (os => streams.value.log.info(os))
    },
    awesomeOsStore := {
      val content = awesomeOsList.value mkString "\n"
      val file = target.value / awesomeOsFileName.value
      IO.write(file, content, Charset forName "UTF-8")
      file
    }
  )

}