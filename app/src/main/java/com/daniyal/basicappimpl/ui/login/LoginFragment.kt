package com.daniyal.basicappimpl.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.home.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint



class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    override fun getFragmentLayout() = R.layout.fragment_login

}