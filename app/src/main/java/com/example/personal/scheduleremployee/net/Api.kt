package com.example.personal.scheduleremployee.net

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Api{
    lateinit var client:Retrofit

    fun init(){
        client = Retrofit.Builder()
                .baseUrl("https://api.ipshorisoes.org/webservice/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .validateEagerly(true)
                .build()
    }

    inline fun <reified T> create(): T = client.create(T::class.java)

}