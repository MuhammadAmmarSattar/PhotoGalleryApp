package com.app.projectname.ui.postLogin.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.projectname.databinding.FragmentNotificationBinding
import com.app.projectname.ui.base.BaseFragment

class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding = FragmentNotificationBinding.inflate(inflater, container, false)

}