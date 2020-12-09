package com.app.projectname.data.repository.photo.remote.service

import com.app.projectname.data.repository.photo.remote.request.GetPhotoRequest
import com.app.projectname.data.repository.photo.remote.response.PhotoResponse
import com.app.projectname.data.repository.photo.remote.response.PhotosResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotoService {

    @POST("/decrypted/photo")
    suspend fun getPhoto(@Body getPhotoRequest: GetPhotoRequest): PhotoResponse


    @POST("/decrypted/photos")
    suspend fun getPhotos(@Body getPhotoRequest: GetPhotoRequest): PhotosResponse


    @GET("/photos")
    suspend fun getPhotos(@Query("page") page: Int): PhotosResponse
}