package com.example.personal.scheduleremployee.di

import com.example.personal.scheduleremployee.net.LoginApi
import com.example.personal.scheduleremployee.ui.login.LoginViewModel
import com.example.personal.scheduleremployee.ui.scan.ScanViewModel
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
                .baseUrl("https://api.ipshorisoes.org/webservice/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    single<LoginApi> { get<Retrofit>().create(LoginApi::class.java) }

    viewModel<LoginViewModel>()
    viewModel<ScanViewModel>()

}