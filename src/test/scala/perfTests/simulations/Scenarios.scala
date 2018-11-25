package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.simulations.RHDRequests._
import scala.concurrent.duration._

trait Scenarios {

  lazy val baseUrl = sys.env.getOrElse("RHD_PERF_TEST_URL", "https://developers.dev.redhat.com")

  val authenticatedDrupalUser = scenario("Authenticated RHD Drupal User")
    .exec(navigateHome)
    .pause(1 seconds, 10 seconds)
    .exec(loginAsDrupalAdmin)
    .pause(1 seconds, 10 seconds)
    .exec(navigateToMyWorkbench)
    .pause(1 seconds, 10 seconds)
    .exec(navigateToContent)
    .pause(1 seconds, 10 seconds)

  val unauthenticatedRHDUser = scenario("Unauthenticated RHD User")
    .exec(navigateHome)
    .pause(1 seconds, 10 seconds)
    .exec(search)
    .pause(1 seconds, 10 seconds)
    .exec(navigateToProducts)
    .pause(1 seconds, 10 seconds)
    .exec(navigateToEvents)
    .pause(1 seconds, 10 seconds)


}
