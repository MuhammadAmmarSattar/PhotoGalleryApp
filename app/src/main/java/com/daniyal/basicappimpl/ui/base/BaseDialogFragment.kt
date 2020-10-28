package com.daniyal.basicappimpl.ui.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.squareup.otto.Bus

abstract class BaseDialogFragment : DialogFragment() {
    protected lateinit var application: ApplicationEntry
    protected lateinit var bus: Bus


    protected var isBusRegistered: Boolean = false


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        application = activity?.application as ApplicationEntry
        bus = application.bus
        bus.register(this)
        isBusRegistered = true
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }


    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Objects.requireNonNull<Window>(dialog?.window)
//                .getAttributes().windowAnimations = R.style.MyAnimation_Window
        }
    }


}