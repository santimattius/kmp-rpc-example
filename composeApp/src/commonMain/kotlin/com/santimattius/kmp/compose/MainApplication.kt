package com.santimattius.kmp.compose

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.santimattius.kmp.compose.di.AppQualifiers
import com.santimattius.kmp.compose.di.applicationModules
import com.santimattius.kmp.compose.navigation.Navigation
import io.ktor.client.HttpClient
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@Composable
fun MainApplication() {
    KoinApplication(application = { modules(applicationModules()) }) {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }
        Navigation()
    }
}
