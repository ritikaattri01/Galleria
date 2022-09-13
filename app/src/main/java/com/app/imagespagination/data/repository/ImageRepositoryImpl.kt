package com.app.imagespagination.data.repository

import com.app.imagespagination.data.local.AppDatabase
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.utils.Resource
import com.app.paginatedimages.data.remote.ImagesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(
    db: AppDatabase,
    private val api: ImagesService
) {
    private val userDb = db.imageDao

    suspend fun getImages(page: Int, limit: Int): Resource<List<ImageEntity>?> {
        val response = api.getImages(page, limit)
        return if (response.isSuccessful) {
            userDb.insertAll(response.body())
            Resource.Success(response.body())
        } else {
            Resource.Failure(response.message())
        }
    }
}