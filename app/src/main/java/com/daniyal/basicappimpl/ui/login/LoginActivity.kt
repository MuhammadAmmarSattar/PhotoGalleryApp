package com.daniyal.basicappimpl.ui.login

import android.content.Intent
import android.os.Bundle
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseActivity
import com.daniyal.basicappimpl.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkLogin()


    }


    private fun checkLogin(){
        //Here we will verify LoginStatus
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}