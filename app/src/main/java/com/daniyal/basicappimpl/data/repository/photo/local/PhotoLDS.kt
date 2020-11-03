package com.daniyal.basicappimpl.data.repository.photo.local

import javax.inject.Inject

class PhotoLDS @Inject constructor(private val photoDao: PhotoDao) {

    suspend fun getAll(): List<Photo> = photoDao.getAll()

    suspend fun save(photo: Photo) = photoDao.insert(photo)

    suspend fun saveAll(list: MutableList<Photo>) = photoDao.insertAll(list)
}