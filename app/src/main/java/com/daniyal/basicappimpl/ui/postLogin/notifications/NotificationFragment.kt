package com.daniyal.basicappimpl.ui.postLogin.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniyal.basicappimpl.databinding.FragmentNotificationBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment

class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding = FragmentNotificationBinding.inflate(inflater, container, false)

}