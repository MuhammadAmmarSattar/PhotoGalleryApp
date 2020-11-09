package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(private val photoRepository: PhotoRepository):BaseViewModel() {
    init {
        viewModelScope.launch {
            photoRepository.getPhotos()
        }
    }
}