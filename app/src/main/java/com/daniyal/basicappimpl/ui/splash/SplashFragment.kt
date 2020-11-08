package com.daniyal.basicappimpl.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.databinding.FragmentSplashBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.login.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<AuthViewModel, FragmentSplashBinding, PhotoRepository>() {
//BaseFragment<AuthViewModel, FragmentSplashBinding, PhotoRepository>
   @Inject
   lateinit var photoRepository:PhotoRepository
    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSplashBinding.inflate(inflater,container,false)


    override fun getFragmentRepository()= photoRepository
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewLifecycleOwner.lifecycleScope.launch {
//            navigate()
//
//        }
//    }
//    override fun getFragmentLayout() = R.layout.fragment_splash
//
//    private suspend fun navigate(){
//        delay(3000)
//        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
//    }
}