package com.santimattius.kmp.compose.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.kmp.compose.core.ui.components.AppBar
import com.santimattius.kmp.compose.core.ui.components.ErrorView
import com.santimattius.kmp.compose.core.ui.components.LoadingIndicator
import com.santimattius.kmp.compose.core.ui.components.NetworkImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    screenModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { AppBar(title = "Compose Skeleton") },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = { screenModel.randomImage() }) {
                Icon(Icons.Default.Refresh, contentDescription = null)
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> LoadingIndicator()

                state.data == null || state.hasError -> {
                    ErrorView(message = "An error occurred while updating the image")
                }

                else -> {
                    Card(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        NetworkImage(
                            imageUrl = state.data!!.url,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .aspectRatio(ratio = (16 / 8).toFloat()),
                        )
                    }
                }
            }
        }
    }
}
