import bet.mgs.platform.SERVICE_KEY
import bet.mgs.platform.SERVICE_MANAGEMENT_ADDRESS
import bet.mgs.platform.SERVICE_MANAGEMENT_PORT
import di.ConfigurationModule
import di.api.APIModule
import di.services.ServicesModule
import org.koin.core.context.startKoin

fun main(args: Array<String>) {

    startKoin {
        printLogger()
        modules(APIModule, ConfigurationModule, ServicesModule)
    }
    TablesApplication.runApp(args)



}