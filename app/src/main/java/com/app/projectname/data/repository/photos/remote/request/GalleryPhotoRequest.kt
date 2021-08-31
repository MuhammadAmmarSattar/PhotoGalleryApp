package com.app.projectname.data.repository.photos.remote.request

data class GalleryPhotoRequest(
    val key : String,
    val searchKeyWord : String,
    val image_type : String,
    var page : Int,
    var per_page : Int
) {
}