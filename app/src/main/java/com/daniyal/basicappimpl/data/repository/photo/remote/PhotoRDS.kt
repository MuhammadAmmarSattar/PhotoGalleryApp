package com.daniyal.basicappimpl.data.repository.photo.remote
import com.daniyal.basicappimpl.data.Result
import com.daniyal.basicappimpl.data.repository.photo.remote.request.GetPhotoRequest
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotosResponse
import com.daniyal.basicappimpl.data.repository.photo.remote.service.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class PhotoRDS @Inject constructor(private val photoService: PhotoService) {
    suspend fun getPhotos(): Result<PhotosResponse> {
        return withContext(Dispatchers.IO) {

            try {
                val response = photoService.getPhotos(GetPhotoRequest())
                // specify dispatcher here so no viewmodel has to specify explicitly
                if (response.isSuccessful) {
                    Result.Success(response.body()!!, "Request Successful")
                } else {
                    Result.Error(Exception("Request Failed - unsuccessful"))

                }
            } catch (e: Exception) {
                Timber.e(e.message)
                Result.Error(Exception("Request Failed - parsing error"))

            }

        }

    }
}