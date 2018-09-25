package com.example.personal.scheduleremployee

import android.app.Application
import com.example.personal.scheduleremployee.data.preferences.UserSession
import com.example.personal.scheduleremployee.di.appModule
import org.koin.android.ext.android.startKoin
import org.koin.core.Koin

class App:Application(){

    override fun onCreate() {
        super.onCreate()
        UserSession.init(this)
        startKoin(this, listOf(appModule))
    }

}