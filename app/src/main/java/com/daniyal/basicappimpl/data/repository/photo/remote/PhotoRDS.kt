package com.daniyal.basicappimpl.data.repository.photo.remote
import com.daniyal.basicappimpl.data.repository.base.BaseRDS
import com.daniyal.basicappimpl.data.repository.photo.remote.request.GetPhotoRequest
import com.daniyal.basicappimpl.data.repository.photo.remote.service.PhotoService
import javax.inject.Inject

class PhotoRDS @Inject constructor(private val photoService: PhotoService):BaseRDS() {

    suspend fun getPhotos()=safeApiCall {
        photoService.getPhotos(GetPhotoRequest())
    }

//
//    suspend fun getPhotos(): Result<PhotosResponse> {
//        return withContext(Dispatchers.IO) {
//
//            try {
//                val response = photoService.getPhotos(GetPhotoRequest())
//                // specify dispatcher here so no viewmodel has to specify explicitly
//                if (response.isSuccessful) {
//                    Result.Success(response.body()!!, "Request Successful")
//                } else {
//                    Result.Error(Exception("Request Failed - unsuccessful"))
//
//                }
//            } catch (e: Exception) {
//                Timber.e(e.message)
//                Result.Error(Exception("Request Failed - parsing error"))
//
//            }
//
//        }
//
//    }
}