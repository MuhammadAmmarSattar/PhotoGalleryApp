package com.daniyal.basicappimpl.ui.preLogin.login.viewmodels

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import com.daniyal.basicappimpl.utils.AuthUtils.isEmailValid

class LoginViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel(application) {
    var email: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var btnSelected: ObservableBoolean = ObservableBoolean(false)

    //Below loginStatus Flag Will be removed as per requirement
    var _loginStatus: MutableLiveData<Boolean> = MutableLiveData()
    var loginStatus: MutableLiveData<Boolean> = _loginStatus

    init {
        loginStatus.value = false
    }


    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected.set(
            isEmailValid(s.toString().trim()) &&
                    password.get().toString().trim().length >= 6
        )

    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected.set(
            isEmailValid(email.get().toString().trim()) &&
                    s.toString().trim().length >= 6
        )


    }

    fun getRegistered() {
        showToast("getRegistered!")
    }

    fun forgotPassword() {
        showToast("forgotPassword!")
    }


    fun login() {
        loginStatus.value = true
//        repository?.doLogin(email?.get().toString(), password.get().toString())
    }


}