package com.app.projectname.data.repository.photos.local.gallery

import com.app.projectname.data.repository.base.BaseLDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryLDS @Inject constructor(private val galleryDao: GalleryDao) :
    BaseLDS<GalleryPhoto>(galleryDao) {

    suspend fun getGallery(): GalleryPhoto? {
        val list = galleryDao.getAll()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }
    fun getGalleryPhotoFlow(): Flow<List<GalleryPhoto>> {
        return galleryDao.getAllFlow()
    }

    suspend fun deleteAll() = galleryDao.deleteAll()
}