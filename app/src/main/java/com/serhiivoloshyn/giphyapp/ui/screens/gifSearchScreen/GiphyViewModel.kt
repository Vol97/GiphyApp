package com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.serhiivoloshyn.giphyapp.models.GeneralGifData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val repository: GiphyRepository
) : ViewModel() {

    private val _searchString = MutableStateFlow(GiphyUiState())

    private val _searchedGifs = MutableStateFlow<PagingData<GeneralGifData>>(PagingData.empty())
    val searchedGifs = _searchedGifs

    fun updateSearchString(searchString: String) {
        _searchString.update {
            it.copy(searchString = searchString)
        }
    }

    fun searchGifs(searchString: String) {
        viewModelScope.launch {
            repository.getSearchGifs(
                searchString = searchString
            ).cachedIn(viewModelScope).collect {
                _searchedGifs.value = it
            }
        }
    }
}