package com.example.waraqawaqalam.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waraqawaqalam.ui.home.HomeDestinatoin
import com.example.waraqawaqalam.ui.home.HomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator(contentPadding: PaddingValues = PaddingValues(0.dp)) {

    val navController: NavHostController = rememberNavController()
    NavHost(
        modifier = Modifier.padding(contentPadding),
        navController = navController,
        startDestination = HomeDestinatoin.route,
        ) {
        composable(route = HomeDestinatoin.route) {
            HomeScreen(
                scrollBehavior = null,
                canGoBack = false
            )
        }
    }
}