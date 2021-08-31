package com.app.projectname.data.repository.photos.remote.service

import com.app.projectname.data.repository.photos.remote.response.GalleryPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryPhotoService {
    @GET("api/")
    suspend fun getPhotos(
        @Query("key") key: String,
        @Query("q") searchKeyWord: String,
        @Query("image_type") imgType: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int): GalleryPhotoResponse
}