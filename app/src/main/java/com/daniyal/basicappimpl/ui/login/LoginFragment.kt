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
import kotlinx.android.synthetic.main.fragment_login.*
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
        setupPagination()
    }

    private fun setupPagination() {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = pagingAdapter
        authViewModel.photosPagination.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                pagingAdapter.submitData(it)
            }
        })
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