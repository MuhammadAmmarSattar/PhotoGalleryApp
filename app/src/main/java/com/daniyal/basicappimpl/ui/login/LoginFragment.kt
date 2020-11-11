package com.daniyal.basicappimpl.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.login.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUiEvents(authViewModel)
        subscribeToObserver()

    }


    fun subscribeToObserver() {
        authViewModel.photos.observe(viewLifecycleOwner) {
            when(it){
                is Result.Success->{
                    it.data
                }
                is Result.Failure->{

                }
            }

        }
    }


//    override fun getFragmentLayout() = R.layout.fragment_login

}