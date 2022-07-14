package configuration

import api.API_Platform
import bet.mgs.platform.Connection
import bet.mgs.platform.SERVICE_KEY
import bet.mgs.platform.SERVICE_MANAGEMENT_ADDRESS
import bet.mgs.platform.SERVICE_MANAGEMENT_PORT
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class CoreConfiguration(private val api: API_Platform) {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    fun getConnection (): Connection {
        LOG.info("host:port:key value: $SERVICE_MANAGEMENT_ADDRESS:$SERVICE_MANAGEMENT_PORT:$SERVICE_KEY")
        val connection = Connection(api)
        Timer().scheduleAtFixedRate(0, 15000) {
            LOG.info("Connecting...")
            connection.connect()
            LOG.error("Disconnect")
        }
        while (!connection.auth)
            Thread.sleep(100)
        LOG.info("Connected")
        return connection
    }
}