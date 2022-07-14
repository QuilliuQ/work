package di

import configuration.CoreConfiguration
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val ConfigurationModule = module {
    singleOf(::CoreConfiguration)
}