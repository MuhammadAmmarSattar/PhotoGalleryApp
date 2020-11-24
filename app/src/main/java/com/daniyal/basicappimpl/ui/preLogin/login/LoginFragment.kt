package com.daniyal.basicappimpl.ui.preLogin.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.postLogin.PostLoginActivity
import com.daniyal.basicappimpl.ui.preLogin.login.viewmodels.LoginViewModel
import com.daniyal.basicappimpl.utils.killSessionAndStartNewActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = loginViewModel
        subscribeUiEvents(loginViewModel)
        subscribeToObservables()
        binding.registration.setOnClickListener {
            loginViewModel.showToast("getRegistered!")

        }
        binding.forgotPassword.setOnClickListener {
            loginViewModel.showToast("ForgotPassword!")
        }
    }


    private fun subscribeToObservables() {
        application.auth.isLoggedIn = false
        loginViewModel.loginStatus.observe(viewLifecycleOwner) {
            if (it) {
                application.auth.isLoggedIn = true
                activity.killSessionAndStartNewActivity(PostLoginActivity::class.java)
            }
        }

        loginViewModel.emailStatus.observe(viewLifecycleOwner) {
            binding.emailLayout.isErrorEnabled = false
            if (it) {
                binding.emailLayout.isErrorEnabled = true
                binding.emailLayout.error = "Invalid Email."
            }
        }

        loginViewModel.passwordStatus.observe(viewLifecycleOwner) {
            binding.passWordLayout.isErrorEnabled = false
            if (it) {
                binding.passWordLayout.isErrorEnabled = true
                binding.passWordLayout.error = "Invalid Password."
            }
        }
    }

}