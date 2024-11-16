package com.example.waraqawaqalam.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.waraqawaqalam.ui.game.DisplayNavigation
import com.example.waraqawaqalam.ui.game.KingdomDisplayScreen
import com.example.waraqawaqalam.ui.home.HomeDestinatoin
import com.example.waraqawaqalam.ui.home.HomeScreen
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorDestinatoin
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    navVM: NavigatorVM = viewModel()
) {

    val navController: NavHostController = rememberNavController()
    val savedIfo by navVM.savedIfo.collectAsState()
    NavHost(
        modifier = Modifier.padding(contentPadding),
        navController = navController,
        startDestination = HomeDestinatoin.route,
        ) {
        composable(
            route = HomeDestinatoin.route,
        ) {
            HomeScreen(
                scrollBehavior = null,
                canGoBack = false,
                goToDisplay = {
                    playerOne: String, playerTwo: String ->
                    navVM.updateNames(playerOne,playerTwo)
                    navController.navigate(route = DisplayNavigation.route) },

            )
        }
        composable(
            DisplayNavigation.route,
        ) {
            KingdomDisplayScreen(
                { navController.navigate(ScoreCulculatorDestinatoin.route) },
                navigateUp = { navController.popBackStack(HomeDestinatoin.route, false) },
                player1 = savedIfo.playerOneName,
                player2 = savedIfo.playerTwoName,
            )
        }
        composable(ScoreCulculatorDestinatoin.route) {
            ScoreCulculatorScreen() { navController.navigate(DisplayNavigation.route) }
        }
    }


}