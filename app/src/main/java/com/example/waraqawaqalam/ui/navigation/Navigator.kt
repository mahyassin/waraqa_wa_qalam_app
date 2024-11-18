package com.example.waraqawaqalam.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waraqawaqalam.AppViewModelProvider
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.gameId
import com.example.waraqawaqalam.data.playerOne
import com.example.waraqawaqalam.data.playerTwo
import com.example.waraqawaqalam.data.totalScore
import com.example.waraqawaqalam.ui.game.DisplayNavigation
import com.example.waraqawaqalam.ui.game.KingdomDisplayScreen
import com.example.waraqawaqalam.ui.home.HomeDestinatoin
import com.example.waraqawaqalam.ui.home.HomeScreen
import com.example.waraqawaqalam.ui.loadGame.LoadDestination
import com.example.waraqawaqalam.ui.loadGame.LoadGameScreen
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorDestinatoin
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator(
    navVM: NavigatorVM = viewModel(factory = AppViewModelProvider .Factory)
) {

    val navController: NavHostController = rememberNavController()
    val gameList = navVM.gamesList
    val editedKingdom by navVM.editedKingdom.collectAsState()


    NavHost(
        navController = navController,
        startDestination = HomeDestinatoin.route,
        ) {
        composable(
            route = HomeDestinatoin.route,
        ) {
            navVM.getKingdoms()
            HomeScreen(
                scrollBehavior = null,
                canGoBack = false,
                goToDisplay = {
                    navVM.addGame(gameList)
                    navController.navigate(route = DisplayNavigation.route)

                },
                gamelist = gameList,
                loadGame = { navVM.getKingdoms()
                           navController.navigate(LoadDestination.route)},
            )
        }
        composable(
            route = LoadDestination.route
        ) {
            LoadGameScreen(
                gamesList = gameList,
                loadGame = {
                    gameId = it
                    navController.navigate(DisplayNavigation.route)
                },
                navigateUp = { navController.popBackStack(HomeDestinatoin.route, false) },
                deleteAll = { navVM.deleteAll() },
                delelteGame = { navVM.deleteGame(it) }
            )
        }
        composable(
            DisplayNavigation.route,
        ) {
            KingdomDisplayScreen(
                {
                    navController.navigate(ScoreCulculatorDestinatoin.route)
                },
                navigateUp = { navController.popBackStack(HomeDestinatoin.route, false) },
                player1 = playerOne,
                player2 = playerTwo,
                editKingdom = { kingdom ->
                    navVM.editKingdom(kingdom)
                    navController.navigate(ScoreCulculatorDestinatoin.editRoute)

                },
                gotoStartScreen = { navController.popBackStack(HomeDestinatoin.route,false) }
            )
        }
        composable(ScoreCulculatorDestinatoin.route) {
            ScoreCulculatorScreen(
                gotoDisplay = { navController.navigate(DisplayNavigation.route) },
                navUp = { navController.popBackStack(DisplayNavigation.route, false) },
                gameId = gameId,
                kingdom = Kingdom()
            )
        }
        composable(route = ScoreCulculatorDestinatoin.editRoute) {
          ScoreCulculatorScreen(
              gotoDisplay = { navController.navigate(DisplayNavigation.route)
                            navVM.updateKingdom(it)
                            },
              navUp = { navController.popBackStack(DisplayNavigation.route, false) },
              gameId = gameId,
              kingdom = editedKingdom
          )
        }
    }


}