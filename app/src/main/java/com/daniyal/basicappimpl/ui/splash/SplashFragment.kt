package com.daniyal.basicappimpl.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.databinding.FragmentSplashBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            navigate()
        }
    }
    override fun getFragmentLayout() = R.layout.fragment_splash

    private suspend fun navigate(){
        delay(3000)
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
}