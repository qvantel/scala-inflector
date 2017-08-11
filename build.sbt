organization in Global := "com.qvantel"
homepage in Global := Some(url("https://github.com/qvantel/scala-inflector"))
startYear in Global := Some(2010)
licenses in Global := Seq(("MIT", url("https://github.com/qvantel/scala-inflector/raw/HEAD/LICENSE")))

version in Global := "1.3.6"

scalaVersion in Global := "2.12.3"
crossScalaVersions in Global := Seq(scalaVersion.value, "2.11.11")

scalacOptions in Global ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8")

resolvers in Global += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val root = project.in(file("."))
  .aggregate(scalaInflectorJVM, scalaInflectorJS)
  .settings(publish := (), publishLocal := (), publishArtifact := false)

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
    exportJars := true)

lazy val scalaInflectorJVM = scalaInflector.jvm
lazy val scalaInflectorJS = scalaInflector.js

