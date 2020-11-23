package com.daniyal.basicappimpl.ui.postLogin.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniyal.basicappimpl.databinding.FragmentFeedsBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment

class FeedsFragment : BaseFragment<FragmentFeedsBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeedsBinding = FragmentFeedsBinding.inflate(inflater, container, false)
}