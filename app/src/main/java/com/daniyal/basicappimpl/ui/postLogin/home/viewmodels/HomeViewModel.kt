package com.daniyal.basicappimpl.ui.postLogin.home.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel(application) {

    val tabDetails = mutableListOf(
        Pair(R.string.feeds, R.drawable.feeds),
        Pair(R.string.market_place, R.drawable.marketplace),
        Pair(R.string.forums, R.drawable.forums),
        Pair(R.string.notifications, R.drawable.notification)
    )

    init {

    }

}