package com.app.projectname.data.repository.photo.remote.response

import com.app.projectname.data.repository.base.BaseResponse


data class PhotoResponse constructor(
    val photo: PhotoDTO
) : BaseResponse()