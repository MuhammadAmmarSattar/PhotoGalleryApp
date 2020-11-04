package com.daniyal.basicappimpl.ui.home.viewmodels

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import com.daniyal.basicappimpl.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(application: Application) :
    BaseViewModel(application) {

    fun getData() {
        //interactor.getData();
        showLoader(true)
        Handler(Looper.getMainLooper()).postDelayed({
            showLoader(false)
        }, 5000)
    }

}
