package com.app.imagespagination.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.utils.Constants.ENDING_PAGE
import com.app.imagespagination.utils.Constants.STARTING_PAGE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MoviePagingSource @Inject constructor(
    private val repo: ImageRepositoryImpl,
) :
    PagingSource<Int, ImageEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ImageEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageEntity> {

        val page = params.key ?: STARTING_PAGE

        return try {
            val response = repo.getImages(page, 20)
            LoadResult.Page(
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (page == ENDING_PAGE) null else page + 1,
                data = response
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}