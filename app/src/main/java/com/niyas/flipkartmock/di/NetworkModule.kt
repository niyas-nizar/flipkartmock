package com.niyas.flipkartmock.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.niyas.flipkartmock.data.ApiInterface
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {


    single {
        OkHttpClient.Builder()
            .build()
    }

    single<Gson> {
        GsonBuilder().apply {
            setLenient()
            disableHtmlEscaping()

        }.create()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://my-json-server.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiInterface::class.java) }
}



