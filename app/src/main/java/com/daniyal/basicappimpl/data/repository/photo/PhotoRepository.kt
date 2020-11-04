package com.daniyal.basicappimpl.data.repository.photo
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.base.BaseDao
import com.daniyal.basicappimpl.data.repository.photo.local.Photo
import com.daniyal.basicappimpl.data.repository.photo.local.PhotoDao
import com.daniyal.basicappimpl.data.repository.photo.local.PhotoLDS
import com.daniyal.basicappimpl.data.repository.photo.remote.PhotoRDS
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val photoLDS: PhotoLDS, private val photoRDS: PhotoRDS) {

    suspend fun getPhotos(): Result<Photo> {
        val resultLDS = photoLDS.getAll()
        return if (resultLDS.isEmpty()) {
            when (val resultRDS = photoRDS.getPhotos()) {
                is Result.Success -> {
                    val listRDS = resultRDS.data.photos
                    val listLDS = listRDS.map { Photo(it.id, it.desc, it.likes) }
                    photoLDS.saveAll(listLDS as MutableList<Photo>)
                    val resultLDS = photoLDS.getAll()
                    Result.Success(resultLDS[0])
                }
                is Result.Error -> {
                    resultRDS
                }
            }
        } else {
            Result.Success(resultLDS[0])
        }
    }
}