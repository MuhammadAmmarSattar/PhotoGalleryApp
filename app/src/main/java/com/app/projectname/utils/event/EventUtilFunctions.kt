package com.app.projectname.utils.event

import android.content.Context
import android.view.View
import android.widget.Toast
import com.app.projectname.utils.ProgressDialog
import com.google.android.material.snackbar.Snackbar

object EventUtilFunctions {
    fun showToast(message: String, context: Context) {
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

    fun showSnackbar(view: View, message: String, action: (() -> Unit)? = null) {

        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackbar.setAction("Retry") {
                it()
            }

        }
        snackbar.show()
    }
}

