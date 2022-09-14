package com.app.imagespagination.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.data.repository.ImagesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val paging: ImagesPagingSource) : ViewModel() {

    fun getListData(): Flow<PagingData<ImageEntity>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { paging }).flow.cachedIn(viewModelScope)
    }
}