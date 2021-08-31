package com.app.projectname.ui.preLogin.gallery.favourite.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.projectname.data.repository.photos.GalleryPhotosRepository
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
     application: Application,
    private val galleryPhotosRepository: GalleryPhotosRepository
):BaseViewModel(application) {

    private val _photo: MutableLiveData<List<GalleryPhoto>> = MutableLiveData()
    val photo: LiveData<List<GalleryPhoto>> = _photo


    init {
        getGalleryPhotos()
    }


    private fun getGalleryPhotos() {
        viewModelScope.launch {
            galleryPhotosRepository.getGalleryFlow().collectLatest {
                _photo.value = it
            }
        }
    }

}