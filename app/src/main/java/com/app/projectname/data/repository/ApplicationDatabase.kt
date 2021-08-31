package com.app.projectname.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.projectname.data.repository.photos.local.gallery.GalleryDao
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto

@Database(entities = [GalleryPhoto::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun galleryPhotoDao(): GalleryDao


}
