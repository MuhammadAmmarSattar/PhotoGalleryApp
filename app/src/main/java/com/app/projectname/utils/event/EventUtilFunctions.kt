package com.app.projectname.utils.event

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.app.projectname.utils.ProgressDialog
import com.app.projectname.utils.safeNavigateFromNavController
import com.google.android.material.snackbar.Snackbar

object EventUtilFunctions {
    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(message: String) {
    }

    fun showLoader(show: Boolean, customProgressDialog: ProgressDialog?) {
        if (show) {
            customProgressDialog?.apply {
                if (!isShowing) show()
            }
        } else {
            customProgressDialog?.apply {
                dismiss()
            }
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
    fun navigateByDirections(fragment: Fragment, navDirections: NavDirections) {
    fragment.safeNavigateFromNavController(navDirections)
    }

}

