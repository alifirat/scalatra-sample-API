import org.scalatra.test.scalatest.ScalatraSuite
import org.scalatest.FunSuite
import sampleApi.controllers.GreetingController

class GreetingControllerTests extends ScalatraSuite  with FunSuite {
  addServlet(classOf[GreetingController], "/*")

  test("simple get") {
    get("/bob") {
      status should equal (200)
      body should include ("Hello bob")
    }
  }
} 