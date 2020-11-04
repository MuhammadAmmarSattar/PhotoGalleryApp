package com.daniyal.basicappimpl.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.daniyal.basicappimpl.utils.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
     var progressDialogController: SingleLiveEvent<Boolean> = SingleLiveEvent()

}