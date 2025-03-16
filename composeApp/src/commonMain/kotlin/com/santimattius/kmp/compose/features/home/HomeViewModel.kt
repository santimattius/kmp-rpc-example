package com.santimattius.kmp.compose.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.kmp.compose.core.data.PictureRepository
import com.santimattius.kmp.compose.core.domain.Picture
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: Picture? = null,
)

class HomeViewModel(
    private val repository: PictureRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, hasError = true) }
    }

    init {
        randomImage()
    }

    fun randomImage() {
        _state.update { it.copy(isLoading = true, hasError = false) }
        viewModelScope.launch(exceptionHandler) {
            repository.random().onSuccess { picture ->
                _state.update { it.copy(isLoading = false, data = picture) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, hasError = true) }
            }
        }
    }
}