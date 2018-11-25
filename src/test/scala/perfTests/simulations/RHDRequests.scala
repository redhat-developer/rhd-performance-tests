package perfTests.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object RHDRequests extends Simulation {

  lazy val drupalUser = System.getProperty("drupalUser")
  lazy val drupalPassword = System.getProperty("drupalUserPassword")

  lazy val searchFeeder = Array("cdk", "containers", "openshift")
  lazy val searchString = Random.shuffle(searchFeeder.toList).head

  def navigateHome =
    http("Navigate To Home Page")
      .get("/")
      .check(status.is(200))
      .check(regex("00fef0cf90c42f3e40921fb3370e520a").exists)

  def loginAsDrupalAdmin =
    http("Login as Drupal Admin")
      .post("/user/login": String)
      .formParam("name", s"$drupalUser": String)
      .formParam("pass", s"$drupalPassword": String)
      .formParam("form_id", "user_login_form")
      .check(status.is(200))
      .check(regex("Member for").exists)

  def navigateToMyWorkbench =
    http("Navigate To My Workbench")
      .get("/admin/workbench")
      .check(status.is(200))
      .check(regex("automated-tests-user's profile").exists)

  def navigateToContent =
    http("Navigate To My Content")
      .get("/admin/content")
      .check(status.is(200))
      .check(regex("Content").exists)

  def navigateToProducts =
    http("Navigate To products")
      .get("/products")
      .check(status.is(200))
      .check(regex("Red Hat Products for Developers").exists)

  def navigateToEvents =
    http("Navigate To Events")
      .get("/events")
      .check(status.is(200))
      .check(regex("Developer Events").exists)

  def search =
    http("Search page")
      .get(s"/search/?t=$searchString")
      .check(status.is(200))
      .check(regex("Search").exists)

  def navigateToCheatsheets =
    http("Navigate to cheatsheet")
      .get("/cheat-sheets/advanced-linux-commands/")
      .check(regex("Advanced Linux Commands Cheat Sheet").exists)
      .check(status.is(200))

  def navigateToKCLogin =
    http("Navigate to keycloak login page")
      .get("/login")
      .check(status.is(200))

  def navigateToKCRegister =
    http("Navigate to keycloak register page")
      .get("/register")
      .check(status.is(200))

}
