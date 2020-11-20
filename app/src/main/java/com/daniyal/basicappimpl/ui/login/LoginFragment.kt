package com.daniyal.basicappimpl.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.databinding.FragmentLoginBinding
import com.daniyal.basicappimpl.ui.base.BaseFragment
import com.daniyal.basicappimpl.ui.login.adapter.PagedPhotoAdapter
import com.daniyal.basicappimpl.ui.login.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val authViewModel: AuthViewModel by viewModels()
    private val pagingAdapter = PagedPhotoAdapter()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUiEvents(authViewModel)
        subscribeToObserver()
    }

    fun initiatePagination() {

//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.adapter = pagingAdapter
//        lifecycleScope.launch {
//            authViewModel.getPaginatedPhotos().collectLatest { pagingData ->
//                pagingAdapter.submitData(pagingData)
//            }
//        }
    }

    fun subscribeToObserver() {
        authViewModel.photos.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    it.data
                }
                is Result.Failure -> {

                }
            }

        }
    }


//    override fun getFragmentLayout() = R.layout.fragment_login

}