package com.santimattius.kmp.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santimattius.kmp.compose.features.home.HomeScreen
import com.santimattius.kmp.compose.features.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen {
                with(navController) {
                    popBackStack()
                    navigate(Home)
                }
            }
        }

        composable<Home> {
            HomeScreen()
        }
    }
}