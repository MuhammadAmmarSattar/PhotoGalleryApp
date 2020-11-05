package com.daniyal.basicappimpl.data.repository.photo.local

import com.daniyal.basicappimpl.data.repository.base.BaseLDS
import javax.inject.Inject

class PhotoLDS @Inject constructor(private val photoDao: PhotoDao) : BaseLDS<Photo>(photoDao) {

    suspend fun getAll(): List<Photo> = photoDao.getAll()

}