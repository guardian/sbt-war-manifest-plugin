import sbt._
class WarManifestPluginProject(info: ProjectInfo) extends PluginProject(info) {
  override def managedStyle = ManagedStyle.Maven
  lazy val publishTo = Resolver.file("GitHub Pages", (Path.userHome / "guardian.github.com" / "maven" / "repo-releases").asFile ) 
}
