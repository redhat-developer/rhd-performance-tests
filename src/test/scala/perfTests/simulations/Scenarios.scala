package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.simulations.RHDRequests._
import scala.concurrent.duration._

trait Scenarios {

  val admins = scenario("Authenticated RHD Drupal User")
    .exec(navigateHome)
    .pause(5 seconds, 10 seconds)
    .exec(loginAsDrupalAdmin)
    .pause(5 seconds, 10 seconds)
    .exec(navigateToMyWorkbench)
    .pause(5 seconds, 10 seconds)
    .exec(navigateToContent)
    .pause(5 seconds, 10 seconds)

  val users = scenario("Unauthenticated RHD User")
    .exec(navigateHome)
    .pause(5 seconds, 10 seconds)
    .exec(search)
    .pause(5 seconds, 10 seconds)
    .exec(navigateToProducts)
    .pause(5 seconds, 10 seconds)
    .exec(navigateToEvents)
    .pause(5 seconds, 10 seconds)

}
