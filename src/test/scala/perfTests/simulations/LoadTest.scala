package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.configuration.RHDConfiguration._
import scala.concurrent.duration._

class LoadTest extends Simulation with Scenarios {

  lazy val DRUPAL_USER_FROM: Int = System.getProperty("drupalFrom").toInt
  lazy val DRUPAL_USER_TO: Int = System.getProperty("drupalTo").toInt
  lazy val UNAUTHENTICATED_USER_FROM: Int = System.getProperty("unauthenticatedUserFrom").toInt
  lazy val UNAUTHENTICATED_USER_TO: Int = System.getProperty("unauthenticatedUserTo").toInt
  lazy val DURATION: Int = System.getProperty("duration").toInt

  setUp(
    authenticatedDrupalUser.inject(
      rampConcurrentUsers(DRUPAL_USER_FROM) to DRUPAL_USER_TO during (DURATION minutes),
      constantConcurrentUsers(DRUPAL_USER_TO) during (DURATION minutes))
      .protocols(httpProtocol),
    unauthenticatedRHDUser.inject(
      rampConcurrentUsers(UNAUTHENTICATED_USER_FROM) to UNAUTHENTICATED_USER_TO during (DURATION minutes),
      constantConcurrentUsers(UNAUTHENTICATED_USER_TO) during (DURATION minutes))
      .protocols(httpProtocol)
  )

}
