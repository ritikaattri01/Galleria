package com.app.imagespagination.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.imagespagination.data.local.AppDatabase.Companion.DB_VERSION

@Database(
    entities = [ImageEntity::class],
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract val imageDao: ImageDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "my_db"
    }
}