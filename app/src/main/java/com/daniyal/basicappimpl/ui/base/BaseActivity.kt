package com.daniyal.basicappimpl.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.squareup.otto.Bus


abstract class BaseActivity : AppCompatActivity() {
    lateinit var applicationEntry: ApplicationEntry
    protected lateinit var bus: Bus
    protected var isBusRegistered: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationEntry = application as ApplicationEntry
        bus = applicationEntry.bus
        bus.register(this)
        isBusRegistered = true

    }


    override fun onDestroy() {
        super.onDestroy()

        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }

    }

    override fun finish() {
        super.finish()

        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }
}