package sampleApi.controllers

import org.scalatra.{ScalatraServlet, Ok}
import sampleApi.config.VersionInfo

//this will return the version information of the API in addition to the status of any subcomponents such as the database
class HealthCheckController extends ScalatraServlet {
  get("/") {

    val name = VersionInfo.name
    val version = VersionInfo.version
    val lastBuilt = VersionInfo.lastBuilt

    Ok(s"$name version: $version LastBuilt: $lastBuilt")
  }
}
