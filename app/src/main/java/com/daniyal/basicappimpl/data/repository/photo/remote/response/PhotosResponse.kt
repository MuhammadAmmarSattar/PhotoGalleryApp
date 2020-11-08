package com.daniyal.basicappimpl.data.repository.photo.remote.response

import com.daniyal.basicappimpl.data.repository.base.BaseResponse


data class PhotosResponse constructor(
    val photos: List<PhotoDTO>
) : BaseResponse()