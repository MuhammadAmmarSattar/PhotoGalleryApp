package com.daniyal.basicappimpl.ui.preLogin

import android.os.Bundle
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_login)
    }
}