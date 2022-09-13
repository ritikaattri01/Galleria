package com.app.imagespagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagespagination.data.repository.ImageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: ImageRepositoryImpl) : ViewModel() {

    fun getImages() {
        viewModelScope.launch { repo.getImages(1, 20) }
    }
}