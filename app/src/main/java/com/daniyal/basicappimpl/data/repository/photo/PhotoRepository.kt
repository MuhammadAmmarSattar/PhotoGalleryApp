package com.daniyal.basicappimpl.data.repository.photo

import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.data.repository.photo.local.PhotoLDS
import com.daniyal.basicappimpl.data.repository.photo.mapper.transform
import com.daniyal.basicappimpl.data.repository.photo.remote.PhotoRDS
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val photoLDS: PhotoLDS,
        private val photoRDS: PhotoRDS
) {

    suspend fun getPhotos(): Result<List<Photo>> {
        val resultLDS = photoLDS.getAll()
        return if (resultLDS.isEmpty()) {
            when (val resultRDS = photoRDS.getPhotos()) {
                is Result.Success -> {
                    // Single Source Of Truth -> get data from server -> save to db -> get from db to provide to UI
                    val listRDS = resultRDS.data.photos
                    val listLDS = transform(listRDS)
                    photoLDS.insertAll(listLDS as MutableList<Photo>)
                    val resultLDS = photoLDS.getAll()
                    Result.Success(resultLDS)
                }
                is Result.Error -> {
                    resultRDS
                }
            }
        } else {
            Result.Success(resultLDS)
        }
    }
}