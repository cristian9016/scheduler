package com.example.personal.scheduleremployee.ui.scan

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.personal.scheduleremployee.data.preferences.UserSession
import io.reactivex.Observable

class ScanViewModel:ViewModel() {

    fun logout(context: Context):Observable<Boolean> =
        Observable.fromCallable {
            UserSession.logout(context)
            true
        }
}