package com.daniyal.basicappimpl.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniyal.basicappimpl.databinding.FragmentHomeBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)


}