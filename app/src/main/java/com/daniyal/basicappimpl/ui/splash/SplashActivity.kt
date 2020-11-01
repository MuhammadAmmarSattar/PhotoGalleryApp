package com.daniyal.basicappimpl.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseActivity
import dagger.internal.DaggerCollections

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }
}