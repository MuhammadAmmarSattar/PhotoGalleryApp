package com.daniyal.basicappimpl.ui.preLogin.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniyal.basicappimpl.databinding.FragmentRegisterBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRegisterBinding.inflate(inflater, container, false)

}