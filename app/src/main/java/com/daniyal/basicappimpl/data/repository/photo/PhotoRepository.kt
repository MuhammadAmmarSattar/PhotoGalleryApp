package com.daniyal.basicappimpl.data.repository.photo

import androidx.paging.PagingData
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.base.BaseRepository
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.data.repository.photo.local.PhotoLDS
import com.daniyal.basicappimpl.data.repository.photo.mapper.transform
import com.daniyal.basicappimpl.data.repository.photo.remote.PhotoRDS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.paging.map

import javax.inject.Inject

class PhotoRepository @Inject constructor(
    val photoLDS: PhotoLDS,
    val photoRDS: PhotoRDS
) : BaseRepository() {
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
                is Result.Failure -> {
                    resultRDS
                }
            }
        } else {
            Result.Success(resultLDS)
        }
    }

    fun getPaginatedPhotos(): Flow<PagingData<Photo>> =
        photoRDS.getPaginatedPhotos()
            .map {
                it.map {
                    transform(it)
                }
            }
}