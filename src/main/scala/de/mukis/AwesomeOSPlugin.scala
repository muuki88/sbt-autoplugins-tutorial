package de.mukis

import sbt._
import sbt.Keys.{ streams }

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
    lazy val awesomeOsList = SettingKey[Seq[String]]("awesome-os-list", "A list of awesome operating systems")
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
    awesomeOsPrint := {
      awesomeOsList.value foreach (os => streams.value.log.info(os))
    }
  )

}