package com.daniyal.basicappimpl.utils.event

import android.content.Context
import android.widget.Toast
import com.daniyal.basicappimpl.utils.ProgressDialog

object EventUtilFunctions {
    fun showToast(message: String,context:Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(message: String) {
    }

    fun showLoader(show: Boolean, customProgressDialog: ProgressDialog?) {
        if (show) {
            customProgressDialog?.show()
        } else {
            customProgressDialog?.hide()
        }
    }
}

