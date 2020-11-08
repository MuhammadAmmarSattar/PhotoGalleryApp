package com.daniyal.basicappimpl.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.login.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, PhotoRepository>() {

    @Inject
    lateinit var photoRepository: PhotoRepository


    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)


    override fun getFragmentRepository() = photoRepository


//    override fun getFragmentLayout() = R.layout.fragment_login

}