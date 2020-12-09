package com.app.projectname.ui.preLogin

import android.os.Bundle
import com.app.projectname.R
import com.app.projectname.ui.base.BaseActivity
import com.app.projectname.utils.AuthUtils.hideStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar(window)
        setContentView(R.layout.activity_pre_login)
    }
}