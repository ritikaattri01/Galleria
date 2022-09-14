package com.app.imagespagination.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.app.imagespagination.data.local.AppDatabase
import com.app.imagespagination.utils.Constants
import com.app.imagespagination.utils.Constants.NETWORK_PAGE_SIZE
import com.app.paginatedimages.data.remote.ImagesService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    db: AppDatabase,
    private val api: ImagesService
) {
    private val userDb = db.imageDao

    val images = userDb.items()

    fun getImages(page: Int) = userDb.getImages(page * NETWORK_PAGE_SIZE, 0)

    suspend fun fetchImages(page: Int) {
        val response = api.getImages(page, NETWORK_PAGE_SIZE)

        if (page == Constants.STARTING_PAGE) {
            userDb.clearAll()
        }

        if (response.isSuccessful && response.body() != null) {
            userDb.insertAll(response.body())
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }
}