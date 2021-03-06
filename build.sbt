val Http4sVersion = "0.23.6"
val CirceVersion = "0.14.1"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.2.6"
val MunitCatsEffectVersion = "1.0.6"
val ScalaTestVersion = "3.2.10"

enablePlugins(JavaAppPackaging)

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.7"


lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "roulette",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.6",
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server"    % Http4sVersion,
      "org.http4s"      %% "http4s-jdk-http-client" % "0.5.0",
      "org.http4s"      %% "http4s-dsl"             % Http4sVersion,
      "org.http4s"      %% "http4s-circe"           % Http4sVersion,
      "io.circe"        %% "circe-generic"          % CirceVersion,
      "io.circe"        %% "circe-generic-extras"   % CirceVersion,
      "io.circe"        %% "circe-parser"           % CirceVersion,
      "org.scalatest"   %% "scalatest"              % ScalaTestVersion        % Test,
      "org.scalameta"   %% "munit"                  % MunitVersion            % Test,
      "org.typelevel"   %% "munit-cats-effect-3"    % MunitCatsEffectVersion  % Test,
      "ch.qos.logback"  %  "logback-classic"        % LogbackVersion,
      "org.scalameta"   %% "svm-subs"               % "20.2.0",
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.0" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  )
