package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel @ViewModelInject constructor(
    application: Application,
    private val photoRepository: PhotoRepository
) : BaseViewModel(application) {

    private val _photos: MutableLiveData<Result<List<Photo>>> = MutableLiveData()
    val photos: LiveData<Result<List<Photo>>> = _photos

    private val _photosPagination: MutableLiveData<PagingData<Photo>> = MutableLiveData()
    val photosPagination: LiveData<PagingData<Photo>> = _photosPagination


    init {
        viewModelScope.launch {
//            _photos.value = photoRepository.getPhotos()

            val result = photoRepository.getPaginatedPhotos()
            when (result) {
                is Result.Success -> {
                    result.data
                        .cachedIn(viewModelScope)
                        .collectLatest {
                            _photosPagination.value = it
                        }

                }
                is Result.Error -> {
                    Timber.e(result.exception.message)
                }
            }

        }


    }

}