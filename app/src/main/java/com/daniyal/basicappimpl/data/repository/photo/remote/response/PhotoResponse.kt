package com.daniyal.basicappimpl.data.repository.photo.remote.response

import com.daniyal.basicappimpl.dto.response.BaseResponse


data class PhotoResponse constructor(
    val photo: PhotoDTO
) : BaseResponse()