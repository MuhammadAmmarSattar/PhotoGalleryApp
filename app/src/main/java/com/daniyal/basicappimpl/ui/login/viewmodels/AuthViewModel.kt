package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(application: Application,private val photoRepository: PhotoRepository):BaseViewModel(application) {

    private val _photos:MutableLiveData<Result<List<Photo>>> = MutableLiveData()
     val photos:LiveData<Result<List<Photo>>> = _photos


    init {
        viewModelScope.launch {
           _photos.value= photoRepository.getPhotos()
        }
    }
}