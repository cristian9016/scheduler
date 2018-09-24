package com.example.personal.scheduleremployee.net

import com.example.personal.scheduleremployee.data.models.Login
import com.example.personal.scheduleremployee.data.models.ServerResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("v2/api/login_charac")
    fun login(@Body login: Login):Observable<ServerResponse<String>>

}