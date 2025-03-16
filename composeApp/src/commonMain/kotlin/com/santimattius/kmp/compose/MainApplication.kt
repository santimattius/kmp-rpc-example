package com.santimattius.kmp.compose

import androidx.compose.runtime.Composable
import com.santimattius.kmp.compose.di.applicationModules
import com.santimattius.kmp.compose.navigation.Navigation
import org.koin.compose.KoinApplication

@Composable
fun MainApplication() {
    KoinApplication(application = { modules(applicationModules()) }) {
        Navigation()
    }
}
