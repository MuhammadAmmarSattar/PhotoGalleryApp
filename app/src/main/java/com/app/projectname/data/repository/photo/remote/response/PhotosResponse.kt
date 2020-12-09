package com.app.projectname.data.repository.photo.remote.response

import com.app.projectname.data.repository.base.BaseResponse


data class PhotosResponse constructor(
    val photos: List<PhotoDTO>
) : BaseResponse()