package com.santimattius.kmp.compose.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.compose.core.ui.components.AppBar
import com.santimattius.kmp.compose.core.ui.components.ErrorView
import com.santimattius.kmp.compose.core.ui.components.LoadingIndicator
import com.santimattius.kmp.compose.features.home.grid.CharacterGrid
import com.santimattius.kmp.compose.features.home.list.CharacterList
import com.santimattius.kmp.shared.LayoutType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    screenModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { AppBar(title = "kotlinx.rpc") },
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> LoadingIndicator()

                state.hasError -> {
                    ErrorView(message = "An error occurred while updating data")
                }

                state.data == null -> {
                    ErrorView(message = "Empty Layout Definition")
                }

                else -> {
                    val data = state.data!!
                    when (data.type) {
                        LayoutType.List -> {
                            CharacterList(characters = data.items)
                        }

                        LayoutType.Grid -> {
                            CharacterGrid(characters = data.items)
                        }
                    }
                }
            }
        }
    }
}
