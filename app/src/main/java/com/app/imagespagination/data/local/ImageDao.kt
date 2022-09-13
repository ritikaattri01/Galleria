package com.app.imagespagination.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Query("Select * from images ORDER BY id ASC LIMIT :limit")
    suspend fun getImages(limit: Int): List<ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<ImageEntity>?)

    @Query("DELETE FROM images")
    suspend fun clearAll()
}