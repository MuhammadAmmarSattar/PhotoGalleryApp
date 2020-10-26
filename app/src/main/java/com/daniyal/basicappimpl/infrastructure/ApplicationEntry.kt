package com.daniyal.basicappimpl.infrastructure

import android.app.Application
import com.squareup.otto.Bus
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationEntry : Application() {
    lateinit var bus: Bus

    init {
        bus=Bus()
    }



}