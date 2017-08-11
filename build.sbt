organization in ThisBuild := "com.qvantel"
homepage in ThisBuild := Some(url("https://github.com/qvantel/scala-inflector"))
startYear in ThisBuild := Some(2010)
licenses in ThisBuild := Seq(("MIT", url("https://github.com/qvantel/scala-inflector/raw/HEAD/LICENSE")))

version in ThisBuild := "1.3.6"

scalaVersion in ThisBuild := "2.12.3"

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8")

resolvers in ThisBuild += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

publishArtifact in Test := false

publishMavenStyle in ThisBuild := true

lazy val root = project.in(file("."))
  .aggregate(scalaInflectorJVM, scalaInflectorJS)
  .settings(publishArtifact := false, publish := {}, publishLocal := {})

lazy val scalaInflector = crossProject.in(file("."))
  .settings(
    name := "scala-inflector",
    libraryDependencies ++= Seq("org.scalatest" %%% "scalatest" % "3.0.1" % "test"),
    pomExtra := (
      <scm>
        <connection>scm:git:git://github.com/backchatio/scala-inflector.git</connection>
        <developerConnection>scm:git:git@github.com:backchatio/scala-inflector.git</developerConnection>
        <url>https://github.com/backchatio/scala-inflector</url>
      </scm>
        <developers>
          <developer>
            <id>casualjim</id>
            <name>Ivan Porto Carrero</name>
            <url>http://flanders.co.nz/</url>
          </developer>
          <developer>
            <id>liff</id>
            <name>Olli Helenius</name>
            <url>https://github.com/liff/</url>
          </developer>
          <developer>
            <id>Doikor</id>
            <name>Aki Huttunen</name>
            <url>http://doikor.fi/</url>
          </developer>
        </developers>
      ),
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
    crossScalaVersions := Seq(scalaVersion.value, "2.11.11")
  )

lazy val scalaInflectorJVM = scalaInflector.jvm
lazy val scalaInflectorJS = scalaInflector.js

