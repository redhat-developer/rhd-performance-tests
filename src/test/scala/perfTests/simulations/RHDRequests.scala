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

  def navigateToFuseConnectors =
    http("Navigate To Fuse Community Page")
      .get("/products/fuse/connectors/")
      .check(status.is(200))
      .check(regex("Red Hat Fuse").exists)

  def navigateToRHELDocsAndAPIs =
    http("Navigate To RHEL Docs and APIs Page")
      .get("/products/rhel/docs-and-apis/")
      .check(status.is(200))
      .check(regex("Docs and APIs").exists)

  def navigateToBuildMicroservicesPage =
    http("Navigate To Build Microservices Page")
      .get("/topics/microservices/")
      .check(status.is(200))
      .check(regex("Build Microservices").exists)

  def navigateToRHELDownload =
    http("Navigate To RHEL Download Page")
      .get("/products/rhel/download/")
      .check(status.is(200))
      .check(regex("Red Hat Enterprise Linux").exists)

}
