package com.app.imagespagination.presentation.listing

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.data.repository.ImageRepository
import com.app.imagespagination.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val repo: ImageRepository) : ViewModel() {

    var pageNo = Constants.STARTING_PAGE

    fun fetchList(): LiveData<List<ImageEntity>> {
        fetch()
        return repo.getImages(pageNo)
    }

    private fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.fetchImages(page = pageNo++)
            } catch (e: Exception) {
                Log.d(TAG, "fetchList: ${e.message}")
            }
        }
    }
}