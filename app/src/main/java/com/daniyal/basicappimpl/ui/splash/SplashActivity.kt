package com.daniyal.basicappimpl.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseActivity
import com.daniyal.basicappimpl.ui.home.MainActivity
import com.daniyal.basicappimpl.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.internal.DaggerCollections

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToLogin()

    }


    private fun navigateToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}