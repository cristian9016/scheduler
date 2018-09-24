package com.example.personal.scheduleremployee.data.preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object UserSession{
    private val PREFERENCE_LOGGED = "isLogged"
    private val PREFERENCE_ID_USER = "idUser"

    private lateinit var preference : SharedPreferences

    var logged:Boolean
    get() = preference.getBoolean(PREFERENCE_LOGGED,false)
    set(value) = preference.edit().putBoolean(PREFERENCE_LOGGED,value).apply()

    var idUser:String
    get() = preference.getString(PREFERENCE_ID_USER,"")
    set(value) = preference.edit().putString(PREFERENCE_ID_USER,value).apply()

    fun init(context:Context){
        preference = context.getSharedPreferences("sessionpreference", Activity.MODE_PRIVATE)
    }

    fun logout(context: Context){
        preference.edit().putString(PREFERENCE_ID_USER,"").apply()
        preference.edit().putBoolean(PREFERENCE_LOGGED,false).apply()
    }
}