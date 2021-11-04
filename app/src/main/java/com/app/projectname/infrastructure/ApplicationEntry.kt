package com.app.projectname.infrastructure

import android.app.Application
import com.squareup.otto.Bus
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ApplicationEntry : Application() {
    var bus: Bus = Bus()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }


}