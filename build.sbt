import sbtcrossproject.CrossPlugin.autoImport.crossProject
lazy val root = project.in(file("."))
  .aggregate(scalaInflectorJVM, scalaInflectorJS)
  .settings(publishArtifact := false, publish := {}, publishLocal := {})
  .settings(inThisBuild(List(
    organization := "com.qvantel",
    homepage     := Some(url("https://github.com/qvantel/scala-inflector")),
    startYear    := Some(2010),
    licenses     := Seq(("MIT", url("https://github.com/qvantel/scala-inflector/raw/HEAD/LICENSE"))),
    version      := "1.4.0",
    scalaVersion := "2.13.3",
    crossScalaVersions := Seq(scalaVersion.value, "2.12.3", "2.11.11"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-explaintypes",
      "-feature",
      "-target:jvm-1.8",
      "-unchecked",
      // advanced
      "-Xcheckinit",
      "-Xfuture",
      "-Xlog-reflective-calls",
      "-Xlog-free-terms",
      "-Xlog-free-types",
      "-Xverify",
      // private
      "-Yrangepos",
      "-Ywarn-dead-code",
      "-Ywarn-value-discard"
    ),
    scalacOptions ++= {
      if (scalaVersion.value startsWith "2.11.") {
        Seq(
          "-Xlint:_",
          "-Ywarn-unused",
          "-Ywarn-unused-import",
          "-Yno-adapted-args",
          "-Ywarn-inaccessible",
          "-Ywarn-infer-any",
          "-Ywarn-nullary-override",
          "-Ywarn-nullary-unit"
        )
      } else Seq.empty
    },
    resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")))

lazy val scalaInflector = crossProject(JSPlatform, JVMPlatform).in(file("."))
  .settings(
    name := "scala-inflector",
    libraryDependencies ++= Seq("org.scalatest" %%% "scalatest" % "3.0.8" % "test"),
    developers := List(
      Developer("casualjim", "Ivan Porto Carrero", "",                         url("http://flanders.co.nz/")),
      Developer("liff",      "Olli Helenius",      "liff@iki.fi",              url("https://github.com/liff/")),
      Developer("Doikor",    "Aki Huttunen",       "aki.huttunen@qvantel.com", url("https://doikor.fi/"))),

    scmInfo := Some(ScmInfo(
      url("https://github.com/qvantel/scala-inflector"),
      "scm:git:git://github.com/qvantel/scala-inflector.git",
      "scm:git:git://github.com/qvantel/scala-inflector.git")),

    packageOptions += Package.ManifestAttributes(
      "Created-By" -> "Simple Build Tool",
      "Built-By" -> System.getProperty("user.name"),
      "Build-Jdk" -> System.getProperty("java.version"),
      "Specification-Title" -> name.value,
      "Specification-Version" -> version.value,
      "Specification-Vendor" -> organization.value,
      "Implementation-Title" -> name.value,
      "Implementation-Version" -> version.value,
      "Implementation-Vendor-Id" -> organization.value,
      "Implementation-Vendor" -> organization.value),

    publishMavenStyle := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    pomIncludeRepository := { x => false },
    exportJars := true,
    crossScalaVersions := Seq(scalaVersion.value, "2.12.3", "2.11.11")
  )

lazy val scalaInflectorJVM = scalaInflector.jvm
lazy val scalaInflectorJS = scalaInflector.js

