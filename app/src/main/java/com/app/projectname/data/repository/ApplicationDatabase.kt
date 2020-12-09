package com.app.projectname.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.projectname.data.repository.photo.local.Photo
import com.app.projectname.data.repository.photo.local.PhotoDao

@Database(entities = [Photo::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

}
