package com.daniyal.basicappimpl.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.databinding.FragmentSplashBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>()  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun getFragmentLayout() = R.layout.fragment_login

}