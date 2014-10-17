name := "awesome-os"
organization := "de.mukis"

scalaVersion in Global := "2.10.2"

sbtPlugin := true

// Settings to build a nice looking plugin site
site.settings
com.typesafe.sbt.SbtSite.SiteKeys.siteMappings <+= (baseDirectory) map { dir => 
  val nojekyll = dir / "src" / "site" / ".nojekyll"
  nojekyll -> ".nojekyll"
}
site.sphinxSupport()
site.includeScaladoc()

// enable github pages
ghpages.settings
git.remoteRepo := "git@github.com:muuki88/sbt-autoplugins-tutorial.git"

// Scripted - sbt plugin tests
scriptedSettings
scriptedLaunchOpts <+= version apply { v => "-Dproject.version="+v }
