package com.aranirahan.myfootballapi

import android.app.Activity
import android.util.Log

fun Activity.logd(message: String){
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}