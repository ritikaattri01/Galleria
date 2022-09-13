package com.app.imagespagination.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "width") val width: Int? = null,
    @ColumnInfo(name = "height") val height: Int? = null,
    @ColumnInfo(name = "url") val url: String? = null,
    @ColumnInfo(name = "download_url") val downloadUrl: String? = null
)