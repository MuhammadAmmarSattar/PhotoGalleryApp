package com.daniyal.basicappimpl.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.daniyal.basicappimpl.utils.SingleLiveEvent

class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var progressDialogController: SingleLiveEvent<Boolean>? = null
    init {
        progressDialogController = SingleLiveEvent()
    }

}