package sampleApi.controllers

import org.scalatra.ScalatraServlet
import org.slf4j.{LoggerFactory}

class GreetingController extends ScalatraServlet {
  val logger =  LoggerFactory.getLogger(getClass)

  get("/") {
    logger.info("We have entered a spectacular binary star system in the Kavis Alpha sector");
    "Hello world"
  }

  get("/:name") {
    val name = params.getOrElse("name", "world")
    "Hello " + name
  }
}
