package de.mukis

import sbt._

object AwesomeMacPlugin extends AutoPlugin {

  object autoImport {
    /** Custom configuration scope */
    lazy val Mac = config("awesomeMac")
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
  override lazy val projectSettings = inConfig(Mac)(AwesomeOSPlugin.projectSettings) ++ settings

  /**
   * the windows specific settings
   */
  private lazy val settings: Seq[Setting[_]] = Seq(
    awesomeOsList in Mac := Seq(
      "MacOS Maverick",
      "MacOS Yosemite",
      "iOS 6",
      "iOS 7"),
    // add awesome os to the general list
    awesomeOsList ++= (awesomeOsList in Mac).value
  )
}