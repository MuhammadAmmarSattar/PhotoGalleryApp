package com.daniyal.basicappimpl.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.squareup.otto.Bus

abstract class BaseFragment : Fragment() {

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = (context as Activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = activity.application as ApplicationEntry
        bus = application.bus
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

}