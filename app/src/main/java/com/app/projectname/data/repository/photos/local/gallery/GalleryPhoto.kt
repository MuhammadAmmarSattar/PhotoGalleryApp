package com.app.projectname.data.repository.photos.local.gallery

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class GalleryPhoto(
    @PrimaryKey
    val id: Int,
    val previewURL: String,
    val likes: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,

):Parcelable{

}