package com.example.personal.scheduleremployee.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import io.reactivex.Observable
import org.jetbrains.anko.toast

fun EditText.text(): String = text.toString()

fun ViewGroup.inflate(@LayoutRes layout: Int) = LayoutInflater.from(context).inflate(layout, this, false)

inline fun <reified T : ViewModel> AppCompatActivity.buildViewModel(): T
        = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.buildViewModel(): T
        = ViewModelProviders.of(this).get(T::class.java)

fun AppCompatActivity.validateForm(message: Int,
                                   vararg fields: String): Observable<List<String>>
        = Observable.create<List<String>> {
    if (fields.contains("")) toast(message)
    else it.onNext(fields.toList())
    it.onComplete()
}