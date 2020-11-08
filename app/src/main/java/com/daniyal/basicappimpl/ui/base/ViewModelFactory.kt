package com.daniyal.basicappimpl.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniyal.basicappimpl.data.repository.base.BaseRepository
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.ui.login.viewmodels.AuthViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return when{
           modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as PhotoRepository) as T
           else -> throw IllegalArgumentException("ViewModelClass Not Found")

       }
    }
}