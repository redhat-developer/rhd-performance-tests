package perfTests.simulations

import io.gatling.core.Predef._
import perfTests.configuration.RHDConfiguration._
import scala.concurrent.duration._

class LoadTest extends Simulation with Scenarios {

  lazy val maxDuration: Int = System.getProperty("maxDuration").toInt
  lazy val rampUpUsers: Int = System.getProperty("rampUpUsers").toInt
  lazy val rampUpAdmins: Int = System.getProperty("rampUpAdmins").toInt

  setUp(
    users
      .inject(rampUsers(rampUpUsers) during (10 seconds)),
    admins
      .inject(rampUsers(rampUpAdmins) during (10 seconds))
  )
    .maxDuration(maxDuration minutes)
    .protocols(httpProtocol)

}
