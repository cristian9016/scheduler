package com.example.personal.scheduleremployee

import android.app.Application
import com.example.personal.scheduleremployee.data.preferences.UserSession
import com.example.personal.scheduleremployee.net.Api

class App:Application(){

    override fun onCreate() {
        super.onCreate()
        UserSession.init(this)
        Api.init()
    }

}