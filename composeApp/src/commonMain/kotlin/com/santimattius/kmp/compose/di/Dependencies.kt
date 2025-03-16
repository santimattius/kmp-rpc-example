package com.santimattius.kmp.compose.di

import com.santimattius.kmp.compose.core.network.ktorHttpClient
import com.santimattius.kmp.compose.core.network.ktorRcpHttpClient
import com.santimattius.kmp.compose.features.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val sharedModules = module {
    single(qualifier(AppQualifiers.BaseUrl)) { "https://api-picture.onrender.com" }
    single(qualifier(AppQualifiers.HttpClient)) {
        ktorHttpClient(
            baseUrl = get(
                qualifier = qualifier(
                    AppQualifiers.BaseUrl
                )
            )
        )
    }

    single(qualifier(AppQualifiers.RcpClient)) { ktorRcpHttpClient() }

}

val homeModule = module {
    viewModel { HomeViewModel(get(qualifier(AppQualifiers.RcpClient))) }
}


fun applicationModules() = listOf(sharedModules, homeModule)