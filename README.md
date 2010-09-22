Usage
=====

Plugins.scala:

    import sbt._
    class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
      val guRepo = "guardian.github.com" at "http://guardian.github.com/maven/repo-releases"
      val warManifestlugin = "com.gu" % "sbt-war-manifest-plugin" % "0.1"
    }

Project file:

    import sbt._
    import com.gu.Manifest

    class MyProject(info: ProjectInfo) extends DefaultWebProject(info) with Manifest {
      override def artifactBaseName = "my-project"

      def revision = systemOptional[String]("build.vcs.number", "DEV").value
      def build = systemOptional[String]("build.number", "DEV").value
      def versionName = "featureX." + buildName
    }

