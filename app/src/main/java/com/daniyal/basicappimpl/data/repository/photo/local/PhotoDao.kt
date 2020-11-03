package com.daniyal.basicappimpl.data.repository.photo.local

import androidx.room.*
import com.daniyal.basicappimpl.data.repository.base.BaseDao
import com.daniyal.basicappimpl.data.repository.photo.local.Photo


@Dao
interface PhotoDao : BaseDao<Photo> {
    @Query("SELECT * FROM photo")
    suspend fun getAll(): List<Photo>

}