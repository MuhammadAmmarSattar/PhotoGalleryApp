package com.daniyal.basicappimpl.ui.preLogin.login.viewmodels

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import com.daniyal.basicappimpl.utils.AuthUtils.isEmailValid

class LoginViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel(application) {
    var email: ObservableField<String> =  ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var btnSelected: ObservableBoolean = ObservableBoolean(false)



    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected.set(
            isEmailValid(s.toString().trim()) &&
                    password.get().toString().trim().length >= 6
        )

    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected.set(
            isEmailValid(email.get().toString().trim()) &&
                    s.toString().trim().length >= 6)


    }

    fun getRegistered() {
        showToast("getRegistered!")
    }

    fun forgotPassword() {
        showToast("forgotPassword!")
    }


    fun login() {
//        repository?.doLogin(email?.get().toString(), password.get().toString())
    }


}