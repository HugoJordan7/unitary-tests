package com.example.unitarytests.di

import com.example.unitarytests.feature.main.viewModel.MainViewModel
import com.example.unitarytests.source.NumberRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ providerMainViewModel(get()) }
}

private fun providerMainViewModel(numberRepository: NumberRepository) = MainViewModel(numberRepository)