package com.app.projectname.data.repository.photos.local.gallery

import androidx.room.Dao
import androidx.room.Query
import com.app.projectname.data.repository.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao : BaseDao<GalleryPhoto> {
    @Query("SELECT * FROM galleryPhoto")
    suspend fun getAll(): List<GalleryPhoto>

    @Query("SELECT * FROM galleryPhoto")
    fun getAllFlow(): Flow<List<GalleryPhoto>>

    @Query("DELETE FROM galleryPhoto")
    suspend fun deleteAll()
}