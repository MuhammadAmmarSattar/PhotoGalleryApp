package com.app.projectname.ui.postLogin.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.projectname.databinding.FragmentFeedsBinding
import com.app.projectname.ui.base.BaseFragment

class FeedsFragment : BaseFragment<FragmentFeedsBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeedsBinding = FragmentFeedsBinding.inflate(inflater, container, false)
}