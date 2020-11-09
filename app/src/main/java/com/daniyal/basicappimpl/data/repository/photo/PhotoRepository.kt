package com.daniyal.basicappimpl.data.repository.photo

import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.base.BaseRepository
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.data.repository.photo.local.PhotoLDS
import com.daniyal.basicappimpl.data.repository.photo.mapper.transform
import com.daniyal.basicappimpl.data.repository.photo.remote.PhotoRDS
import javax.inject.Inject

class PhotoRepository @Inject constructor(
         val photoLDS: PhotoLDS,
         val photoRDS: PhotoRDS
) :BaseRepository(){
    suspend fun getPhotos() =safeApiCall {
        val resultLDS = photoLDS.getAll()
        return@safeApiCall if (resultLDS.isEmpty()) {
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
                is Result.Failure->{
                }
                else -> Unit
            }
        } else {
            Result.Success(resultLDS)
        }
    }



//    suspend fun getPhotos(): Result<List<Photo>> {
//        val resultLDS = photoLDS.getAll()
//        return if (resultLDS.isEmpty()) {
//            when (val resultRDS = photoRDS.getPhotos()) {
//                is Result.Success -> {
//                    // Single Source Of Truth -> get data from server -> save to db -> get from db to provide to UI
//                    val listRDS = resultRDS.data.photos
//                    val listLDS = transform(listRDS)
//                    photoLDS.insertAll(listLDS as MutableList<Photo>)
//                    val resultLDS = photoLDS.getAll()
//                    Result.Success(resultLDS)
//                }
//                is Result.Error -> {
//                    resultRDS
//                }
//            }
//        } else {
//            Result.Success(resultLDS)
//        }
//    }
}