package com.santimattius.kmp.compose.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.kmp.compose.core.network.DEV_SERVER_HOST
import com.santimattius.kmp.shared.Layout
import com.santimattius.kmp.shared.LayoutServices
import io.ktor.client.HttpClient
import io.ktor.http.encodedPath
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.krpc.streamScoped
import kotlinx.rpc.withService

data class HomeUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: Layout? = null,
)

class HomeViewModel(
    private val client: HttpClient
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.update { it.copy(isLoading = false, hasError = true) }
    }

    private var _services: LayoutServices? = null
    private var job: Job? = null

    init {
        job?.cancel()
        job = registerServices()
    }

    private fun registerServices() = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        _services = getServices()
        _services?.let {
            fetchData(it)
        }
    }

    private suspend fun getServices(): LayoutServices {
        return client.rpc {
            url {
                host = DEV_SERVER_HOST
                port = 8080
                encodedPath = "/api"
            }

            rpcConfig {
                serialization {
                    json()
                }
            }
        }.withService()
    }

    private fun fetchData(services: LayoutServices) {
        viewModelScope.launch(Dispatchers.IO) {
            streamScoped {
                services.subscribeToLayout().collectLatest { layout ->
                    _state.update { it.copy(data = layout) }
                }
            }
        }
    }
}