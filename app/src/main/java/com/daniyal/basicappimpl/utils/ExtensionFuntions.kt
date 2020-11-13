package com.daniyal.basicappimpl.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.daniyal.basicappimpl.data.Result
import com.google.android.material.snackbar.Snackbar
import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException

fun requestBodyToString(request: RequestBody?): String? {
    return try {
        val buffer = Buffer()
        if (request != null) request.writeTo(buffer) else return ""
        buffer.readUtf8()
    } catch (e: IOException) {
        "IO Exception"
    }

}


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}


fun View.snackbar(message:String,action:(()->Unit)?=null){
    val snackbar=Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }

    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Result.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetWorkError->requireView().snackbar("Please check your connection",retry)
        failure.errorCode==400->requireView().snackbar("Content not found",retry)
        else -> {
            val error =failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }

}



