package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    application: Application,
    private val photoRepository: PhotoRepository
) : BaseViewModel(application) {

    lateinit var photosPagination: LiveData<PagingData<Photo>>

    private val _photos: MutableLiveData<Result<List<Photo>>> = MutableLiveData()
    val photos: LiveData<Result<List<Photo>>> = _photos


    init {
        viewModelScope.launch {
            _photos.value = photoRepository.getPhotos()
        }

        photosPagination =
            photoRepository.getPaginatedPhotos()
                .cachedIn(viewModelScope)
                .asLiveData()
    }

}