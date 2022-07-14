import configuration.CoreConfiguration
import configuration.ReadFileConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object TablesApplication : KoinComponent {

    private val coreConfiguration : CoreConfiguration by inject()
    private val readFileConfig : ReadFileConfig by inject()

    fun runApp(args: Array<String>) {
    readFileConfig.readConfig(args)
    coreConfiguration.getConnection()

    }
}
