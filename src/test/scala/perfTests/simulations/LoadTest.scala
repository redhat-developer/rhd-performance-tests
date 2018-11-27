package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.configuration.RHDConfiguration._
import scala.concurrent.duration._

class LoadTest extends Simulation with Scenarios {

  lazy val maxDuration: Int = System.getProperty("maxDuration").toInt
  lazy val rampUpUsers: Int = System.getProperty("rampUpUsers").toInt
  lazy val rampUpAdmins: Int = System.getProperty("noAdmins").toInt

  setUp(
    admins.inject(
      rampUsers(rampUpAdmins) during (60 seconds),
      constantUsersPerSec(rampUpAdmins) during (maxDuration minutes) randomized),
    users.inject(
      rampUsers(rampUpUsers) during (60 seconds),
      constantUsersPerSec(rampUpUsers) during (maxDuration minutes))
  )
    .maxDuration(maxDuration).protocols(httpProtocol)

}
