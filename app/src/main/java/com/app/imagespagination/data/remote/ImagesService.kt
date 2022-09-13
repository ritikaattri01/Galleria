package com.app.paginatedimages.data.remote

import com.app.imagespagination.data.local.ImageEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {

    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int?=null,
    ): Response<List<ImageEntity>>
}