package com.app.projectname.ui.preLogin.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.app.projectname.R
import com.app.projectname.databinding.FragmentLoginBinding
import com.app.projectname.ui.base.BaseFragment
import com.app.projectname.ui.postLogin.PostLoginActivity
import com.app.projectname.ui.preLogin.login.viewmodels.LoginViewModel
import com.app.projectname.utils.killSessionAndStartNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.view.*


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
            findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment2)

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