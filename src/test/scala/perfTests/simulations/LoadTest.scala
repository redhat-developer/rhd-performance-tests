package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.configuration.RHDConfiguration._
import scala.concurrent.duration._

class LoadTest extends Simulation with Scenarios {

  lazy val duration:Int = System.getProperty("duration").toInt

    setUp(
      authenticatedDrupalUser.inject(
        rampConcurrentUsers(1) to 5 during (1 minutes),
        constantConcurrentUsers(5) during (1 minutes)),
      unauthenticatedRHDUser.inject(
        rampConcurrentUsers(1) to 10 during (1 minutes),
        constantConcurrentUsers(10) during (1 minutes))).maxDuration(duration minutes).protocols(httpProtocol)
}
