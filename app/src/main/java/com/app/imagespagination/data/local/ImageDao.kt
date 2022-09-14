package com.app.imagespagination.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Query("SELECT * FROM images LIMIT :limit OFFSET :offSet")
    fun getImages(limit: Int, offSet: Int): LiveData<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<ImageEntity>?)

    @Query("DELETE FROM images")
    suspend fun clearAll()

    @Query("Select count(*) from images")
    fun items(): LiveData<Int>
}