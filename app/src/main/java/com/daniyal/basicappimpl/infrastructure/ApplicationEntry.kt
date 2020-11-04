package com.daniyal.basicappimpl.infrastructure

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniyal.basicappimpl.utils.LocaleContainer
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.squareup.otto.Bus
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationEntry : Application() {
    var bus: Bus = Bus()

    override fun onCreate() {
        super.onCreate()
    }


}