package com.gu

import sbt._

import java.util.Date
import java.util.jar.Attributes

trait Manifest extends FixPackageActionForWebapps {
  def title = artifactBaseName
  def versionName : String
  def revision : String
  def build : String
  def branch = "master"

  override def packageOptions =
    ManifestAttributes(
      "Title" -> artifactBaseName,
      Attributes.Name.IMPLEMENTATION_TITLE.toString -> artifactBaseName,
      Attributes.Name.IMPLEMENTATION_VERSION.toString -> versionName,
      "Revision" -> revision,
      "Build" -> build,
      "Branch" -> branch,
      "Date" -> new Date().toString) ::
        super.packageOptions.toList
}

trait FixPackageActionForWebapps extends BasicWebScalaProject {
  // override package so that we pass the packageOptions to it
  // (for some reason the override in DefaultWebProject passes Nil
  // as this parameter as of sbt 0.7.3)
  override protected def packageAction =
    packageWarAction(temporaryWarPath, webappUnmanaged, warPath, packageOptions) dependsOn (prepareWebapp)
}
