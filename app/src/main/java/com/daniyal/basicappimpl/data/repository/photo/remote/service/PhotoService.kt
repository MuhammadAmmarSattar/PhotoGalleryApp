package com.daniyal.basicappimpl.data.repository.photo.remote.service

import com.daniyal.basicappimpl.data.repository.photo.remote.request.GetPhotoRequest
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotoResponse
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotosResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PhotoService {

    @POST("/decrypted/photo")
    suspend fun getPhoto(@Body getPhotoRequest: GetPhotoRequest): Response<PhotoResponse>


    @POST("/decrypted/photos")
    suspend fun getPhotos(@Body getPhotoRequest: GetPhotoRequest): Response<PhotosResponse>
}