package com.app.projectname.data.repository.photo

import androidx.paging.PagingData
import com.app.projectname.data.Result
import com.app.projectname.data.repository.base.BaseRepository
import com.app.projectname.data.repository.photo.local.Photo
import com.app.projectname.data.repository.photo.local.PhotoLDS
import com.app.projectname.data.repository.photo.mapper.transform
import com.app.projectname.data.repository.photo.remote.PhotoRDS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.paging.map
import java.io.IOException

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

    suspend fun getPaginatedPhotos(): Result<Flow<PagingData<Photo>>> {
        val res = photoRDS.getPaginatedPhotos()
        return if (res is Result.Success) {
            Result.Success(res.data.map {
                it.map {
                    transform(it)
                }
            })

        } else {
            Result.Error(IOException("error"))
        }

    }

}