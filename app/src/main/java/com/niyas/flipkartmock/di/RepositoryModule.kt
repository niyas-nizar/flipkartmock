package com.niyas.flipkartmock.di

import com.niyas.flipkartmock.repository.ChatListRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ChatListRepository(get()) }
}