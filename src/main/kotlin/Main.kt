import bet.mgs.platform.SERVICE_KEY
import bet.mgs.platform.SERVICE_MANAGEMENT_ADDRESS
import bet.mgs.platform.SERVICE_MANAGEMENT_PORT
import di.ConfigurationModule
import di.api.APIModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.context.startKoin

suspend fun main(args: Array<String>) {

    startKoin {
        printLogger()
        modules(APIModule, ConfigurationModule)
    }
    SERVICE_MANAGEMENT_ADDRESS = "spinwin.mgs.bet"
    SERVICE_MANAGEMENT_PORT = 8000
    SERVICE_KEY = "158b21e820da46eaa0d60939d4f71f03"


    HelloApplication().sayHello()

    println("Hello World!")

}