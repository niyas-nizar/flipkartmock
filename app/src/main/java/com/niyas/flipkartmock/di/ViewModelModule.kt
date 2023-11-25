package com.niyas.flipkartmock.di

import com.niyas.flipkartmock.viewmodel.ChatDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatDetailsViewModel(get()) }
}