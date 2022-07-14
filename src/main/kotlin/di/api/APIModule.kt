package di.api

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import api.API_Platform

val APIModule = module {
    singleOf(::API_Platform)
}