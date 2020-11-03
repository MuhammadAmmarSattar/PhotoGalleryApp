package com.daniyal.basicappimpl.ui.home.viewmodels

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import com.daniyal.basicappimpl.utils.Resource

class MainViewModel @ViewModelInject constructor(application: Application) : BaseViewModel(application) {
    init {
        progressDialogController.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            progressDialogController.postValue(false)
        }, 5000)
    }
}