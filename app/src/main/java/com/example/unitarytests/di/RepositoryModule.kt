package com.example.unitarytests.di

import com.example.unitarytests.source.NumberRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providerNumberRepository() }
}

private fun providerNumberRepository() = NumberRepository()