package com.app.projectname.data.repository.photos.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.projectname.data.repository.photos.remote.NETWORK_PAGE_SIZE
import com.app.projectname.data.repository.photos.remote.request.GalleryPhotoRequest
import com.app.projectname.data.repository.photos.remote.response.GalleryPhotoDTO
import com.app.projectname.data.repository.photos.remote.service.GalleryPhotoService
import com.app.projectname.utils.Constants
import com.bumptech.glide.load.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1
class PhotoPagingSource(
    private val galleryPhotoService: GalleryPhotoService,
    private val galleryPhotoRequest: GalleryPhotoRequest
): PagingSource<Int, GalleryPhotoDTO>() {
    override fun getRefreshKey(state: PagingState<Int, GalleryPhotoDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryPhotoDTO> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = galleryPhotoService.getPhotos(
                galleryPhotoRequest.key,
                galleryPhotoRequest.searchKeyWord,
                galleryPhotoRequest.image_type,
                page = pageIndex,
                galleryPhotoRequest.per_page
            )
            val photos = response.photoList
            val nextKey =
                if (photos.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = response.photoList,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX ) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}