package com.example.personal.scheduleremployee.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.example.personal.scheduleremployee.data.models.ServerResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LifeDisposable(owner: LifecycleOwner) : LifecycleObserver {

    init {
        owner.lifecycle.addObserver(this)
    }

    private val dis: CompositeDisposable = CompositeDisposable()

    infix fun add(disposable: Disposable) {
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearDisposable() {
        dis.clear()
    }
}

fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> validateResponse(res: ServerResponse<T>): Observable<T> = Observable.create<T> {
    if (res.state) {
        it.onNext(res.data!!)
        it.onComplete()
    } else throw Throwable(res.error)

}