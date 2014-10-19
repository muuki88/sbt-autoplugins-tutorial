package de.mukis

import sbt._

object AwesomeLinuxPlugin extends AutoPlugin{

  object autoImport {
    /** Custom configuration scope */
    lazy val Linux = config("awesomeLinux")
  }

  import AwesomeOSPlugin.autoImport._
  import autoImport._

  /** This plugin requires the AwesomeOSPlugin to be enabled */
  override def requires = AwesomeOSPlugin

  /** If all requirements are met, this plugin will automatically get enabled */
  override def trigger = allRequirements

  /**
   * 1. Use the AwesomeOSPlugin settings as default and scope them to Linux
   * 2. Override the default settings inside the Linux scope
   */
  override lazy val projectSettings = inConfig(Linux)(AwesomeOSPlugin.projectSettings) ++ settings

  /**
   * the linux specific settings
   */
  private lazy val settings: Seq[Setting[_]] = Seq(
    awesomeOsList in Linux := Seq(
      "Ubuntu 12.04 LTS",
      "Ubuntu 14.04 LTS",
      "Debian Squeeze",
      "Fedora 20",
      "CentOS 6",
      "Android 4.x"),
    // add awesome os to the general list
    awesomeOsList ++= (awesomeOsList in Linux).value
  )
}