import bet.mgs.platform.*
import configuration.CoreConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HelloApplication : KoinComponent {

    // Inject HelloService
    val helloService : CoreConfiguration by inject()

    // display our data
    suspend fun sayHello() {
        withContext(Dispatchers.IO) {
            while (true){
            }
        }
    helloService.getConnection()
    }
}
