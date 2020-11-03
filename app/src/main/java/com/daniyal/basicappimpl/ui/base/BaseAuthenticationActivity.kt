package com.daniyal.basicappimpl.ui.base

import android.os.Bundle

abstract class BaseAuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLoginStatus()
        baseOnCreate(savedInstanceState)
    }

    private fun checkLoginStatus(){

        //IF User has logged In Proceed with Activity Other Wise Navigate User to Login Screen
        //We will get the User Session From DataStore to check If its LoggedIn Or not

    }

    protected abstract fun baseOnCreate(savedInstanceState: Bundle?)
}