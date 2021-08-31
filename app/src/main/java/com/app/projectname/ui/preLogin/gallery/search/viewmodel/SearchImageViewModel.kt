package com.app.projectname.ui.preLogin.gallery.search.viewmodel

import android.app.Application
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.projectname.data.Result
import com.app.projectname.data.repository.photos.GalleryPhotosRepository
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.data.repository.photos.remote.request.GalleryPhotoRequest
import com.app.projectname.ui.base.BaseViewModel
import com.app.projectname.utils.Constants
import com.app.projectname.utils.Constants.Bundle.CARS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchImageViewModel @Inject constructor(
    application: Application,
    private val galleryPhotosRepository: GalleryPhotosRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {
    private val _searchItem: MutableLiveData<PagingData<GalleryPhoto>> = MutableLiveData()
    val searchItem: LiveData<PagingData<GalleryPhoto>> = _searchItem

    init {
            savedStateHandle.get<String>("keyword")?.let {
                showLoader(true)
                getPhotos(it)
            }

    }

     fun getPhotos(searchKeyWord: String? = null) {
         viewModelScope.launch {
             when (val result = galleryPhotosRepository.getPaginatedPhotos(
                GalleryPhotoRequest(
                    key = Constants.Paging.KEY,
                    searchKeyWord = searchKeyWord ?: "Airplane",
                    image_type = Constants.Paging.IMAGE_TYPE_PHOTO,
                    page = Constants.Paging.GET_PHOTO_PAGE_SIZE,
                    per_page = 20
                )
            )) {
                is Result.Success -> {
                    result.data
                        .cachedIn(viewModelScope)
                        .collectLatest {
                            _searchItem.value = it
                        }
                }
                is Result.Failure -> {
                    showToast("Something Went Wrong...")

                }
            }
        }
    }

   suspend fun insertItem(galleryPhoto: GalleryPhoto){
        galleryPhotosRepository.insertImage(galleryPhoto)
    }
//    fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//            getPhotos(view?.text.toString())
//            return true
//        }
//        return false
//    }
}