package com.app.projectname.data.repository.photos

import androidx.paging.PagingData
import androidx.paging.map
import com.app.projectname.data.repository.photos.remote.GalleryPhotoRDS
import com.app.projectname.data.repository.photos.remote.request.GalleryPhotoRequest
import com.app.projectname.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.app.projectname.data.Result
import com.app.projectname.data.repository.photos.local.gallery.GalleryLDS
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.data.repository.photos.mapper.transformIntoPhotoGallery
import kotlinx.coroutines.flow.map

class GalleryPhotosRepository @Inject constructor(private val galleryPhotoRDS: GalleryPhotoRDS,private val galleryPhotoLDS: GalleryLDS) {

    suspend fun getPaginatedPhotos(
        galleryPhotoRequest: GalleryPhotoRequest
    ): Result<Flow<PagingData<GalleryPhoto>>> {
        val res = galleryPhotoRDS.getGalleryPhoto(galleryPhotoRequest)
        return if(res is Result.Success){
            Result.Success(res.data.map {
                    it.map {
                        transformIntoPhotoGallery(it)
                    }
            })
        }else{
          Result.Failure(true, null, null, Constants.Error.SOMETHING_WENT_WRONG)

        }
    }
    suspend fun insertImage(gallery : GalleryPhoto){
        galleryPhotoLDS.insert(gallery)
    }

    fun getGalleryFlow() = galleryPhotoLDS.getGalleryPhotoFlow()


//    suspend fun galleryPhotoInfo(forceFetch: Boolean = false): GalleryPhoto? {
//        val resultLDS = galleryPhotoLDS.getGallery()
//        if(resultLDS!=null && !forceFetch) return resultLDS
//
//    }
}