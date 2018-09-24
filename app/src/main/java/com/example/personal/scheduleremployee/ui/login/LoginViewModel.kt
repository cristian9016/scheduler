package com.example.personal.scheduleremployee.ui.login

import android.arch.lifecycle.ViewModel
import com.example.personal.scheduleremployee.data.models.Login
import com.example.personal.scheduleremployee.net.Api
import com.example.personal.scheduleremployee.net.LoginApi
import com.example.personal.scheduleremployee.util.applySchedulers
import com.example.personal.scheduleremployee.util.validateResponse
import io.reactivex.Observable

class LoginViewModel:ViewModel() {

    val client:LoginApi = Api.create()

    fun login(login: Login):Observable<String> =
            client.login(login)
                    .flatMap { validateResponse(it) }
                    .applySchedulers()


}