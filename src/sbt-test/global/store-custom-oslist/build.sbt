enablePlugins(AwesomeOSPlugin)

name := "simple-test"

version := "0.1.0"

awesomeOsFileName := "another-os.txt"

// this is the scripted test
TaskKey[Unit]("check-os-list") := {
    val list = IO.read(target.value / awesomeOsFileName.value)
    assert(list contains "Ubuntu", "Ubuntu not present in awesome operating systems: " + list)
}