package com.app.projectname.data.repository.photos.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GalleryPhotoResponse(
    @SerializedName("hits")
    @Expose
    val photoList: ArrayList<GalleryPhotoDTO>
){}