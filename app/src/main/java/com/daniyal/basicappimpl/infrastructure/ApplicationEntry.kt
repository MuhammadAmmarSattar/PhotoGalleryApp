package com.daniyal.basicappimpl.infrastructure

import android.app.Application
import com.squareup.otto.Bus
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationEntry : Application() {
    var bus: Bus = Bus()
}