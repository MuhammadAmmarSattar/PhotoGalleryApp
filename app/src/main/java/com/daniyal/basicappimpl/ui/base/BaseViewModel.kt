package com.daniyal.basicappimpl.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.daniyal.basicappimpl.utils.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var progressDialogController: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun getProgressDialogController(): SingleLiveEvent<Boolean> = progressDialogController

    fun showLoader(show: Boolean) {
        progressDialogController.postValue(show)
    }
}