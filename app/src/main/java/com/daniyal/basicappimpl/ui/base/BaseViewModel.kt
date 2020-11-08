package com.daniyal.basicappimpl.ui.base

import androidx.lifecycle.ViewModel
import com.daniyal.basicappimpl.utils.SingleLiveEvent

abstract class BaseViewModel() : ViewModel() {
    private var _progressDialog: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var progressDialog: SingleLiveEvent<Boolean> = _progressDialog

    fun showLoader(show: Boolean) {
        _progressDialog.postValue(show)
    }
}