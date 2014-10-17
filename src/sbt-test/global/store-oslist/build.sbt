enablePlugins(AwesomeOSPlugin)

name := "simple-test"

version := "0.1.0"

TaskKey[Unit]("check-os-list") := {
    val list = IO.read(target.value / awesomeOsFileName.value)
    assert(list contains "Ubuntu", "Ubuntu not present in awesome operating systems: " + list)
}