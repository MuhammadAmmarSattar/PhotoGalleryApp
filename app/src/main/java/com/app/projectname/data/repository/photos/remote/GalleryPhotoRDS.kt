package com.app.projectname.data.repository.photos.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.projectname.data.Result
import com.app.projectname.data.repository.base.BaseRDS
import com.app.projectname.data.repository.photos.pagingsource.PhotoPagingSource
import com.app.projectname.data.repository.photos.remote.request.GalleryPhotoRequest
import com.app.projectname.data.repository.photos.remote.response.GalleryPhotoDTO
import com.app.projectname.data.repository.photos.remote.service.GalleryPhotoService
import com.app.projectname.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 25
class GalleryPhotoRDS @Inject constructor(private val galleryPhotoService: GalleryPhotoService) : BaseRDS() {
    suspend fun getGalleryPhoto(
     galleryPhotoRequest: GalleryPhotoRequest
    ): Result<Flow<PagingData<GalleryPhotoDTO>>> =
        safeApiCall {
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE
                ),
                pagingSourceFactory = {
                    PhotoPagingSource(galleryPhotoService,
                        galleryPhotoRequest
                    )
                }
            ).flow
        }
}