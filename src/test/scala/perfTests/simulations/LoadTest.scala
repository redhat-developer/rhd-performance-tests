package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.configuration.RHDConfiguration._
import scala.concurrent.duration._

class LoadTest extends Simulation with Scenarios {

  lazy val rampUpTime:Int = System.getProperty("rampUpTime").toInt
  lazy val duration:Int = System.getProperty("duration").toInt

    setUp(
      authenticatedDrupalUser.inject(
        rampConcurrentUsers(1) to 5 during (rampUpTime minutes),
        constantConcurrentUsers(5) during (duration minutes))
        .protocols(httpProtocol),
      unauthenticatedRHDUser.inject(
        rampConcurrentUsers(1) to 10 during (rampUpTime minutes),
        constantConcurrentUsers(10) during (duration minutes))
        .protocols(httpProtocol)
  )
}
