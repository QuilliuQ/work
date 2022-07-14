package di

import configuration.CoreConfiguration
import configuration.ReadFileConfig
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val ConfigurationModule = module {
    singleOf(::CoreConfiguration)
    singleOf(::ReadFileConfig)
}