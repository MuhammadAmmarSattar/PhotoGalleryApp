package com.daniyal.basicappimpl.ui.home

import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.RequestManager
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseAuthenticationActivity
import com.daniyal.basicappimpl.utils.LocaleContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseAuthenticationActivity() {

    @Inject
    lateinit var glide: RequestManager

    override fun baseOnCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        _localeContainer.postValue(LocaleContainer.ARABIC)

    }


}