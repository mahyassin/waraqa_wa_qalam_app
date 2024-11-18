package com.example.waraqawaqalam.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waraqawaqalam.data.Game
import com.example.waraqawaqalam.ui.navigation.NavigationDestination
import androidx.compose.ui.window.Dialog
import com.example.waraqawaqalam.AppViewModelProvider
import com.example.waraqawaqalam.data.Kingdom


object HomeDestinatoin: NavigationDestination {
    override val route: String
        get() = "Home"
    override val title: String
        get() = "Home screen"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    scrollBehavior: TopAppBarScrollBehavior?,
    canGoBack: Boolean,
    vm: HomeScreenVM = viewModel(factory = AppViewModelProvider.Factory),
    goToDisplay: () -> Unit,
    loadGame: () -> Unit,
    gamelist: List<Kingdom>,
) {
    val homeUiState by vm.homeUiState.collectAsState()
    Scaffold(
        topBar = { ScreenTopBar(
            scrollBehavior = scrollBehavior,
            canGoBack = canGoBack,
            title = HomeDestinatoin.title
        )}

    ) { innerPadding ->

        if (homeUiState.playersNamed){
            AddNameDialog(
                homeUiState,
                updatePlayerOne = { vm.updatePlayerOne(it) },
                updatePlayerTwo = { vm.updatePlayerTwo(it) },
                removeDialog = { vm.popAddName() },
                goToDisplay = {
                    goToDisplay()
                    vm.submitNames()


                }
            )
        } else {
            StartScreen(
                Modifier.padding(innerPadding),
                gameList = gamelist,
                loadGame = { loadGame() }
            ) { vm.popAddName()
            }
        }
    }
}

@Composable
fun StartScreen(
    modifier: Modifier,
    gameList: List<Kingdom>,
    loadGame: () -> Unit,
    addNames: () -> Unit,



) {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            addNames()

        }) {
            Text("Create a new game")
        }
        if (gameList.isNotEmpty()) {
            Button(onClick = {loadGame()}) {
                Text("Load a game")
            }
        }

    }
}

@Composable
fun AddNameDialog(
    homeUiState: HomeUiState,
    updatePlayerOne: (String) -> Unit,
    updatePlayerTwo: (String) -> Unit,
    removeDialog: () -> Unit,
    goToDisplay: () -> Unit,
) {
    Dialog(
        onDismissRequest = { removeDialog() }
    ) {
       Column() {
            ElevatedCard {
                val modifier: Modifier = Modifier.padding(8.dp)
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    TextField(
                        value = homeUiState.playerOne,
                        onValueChange = { updatePlayerOne(it) },
                        label = {
                            Text("player 1")
                        },
                        modifier = modifier
                    )
                    TextField(
                        value = homeUiState.playerTwo,
                        onValueChange = { updatePlayerTwo(it) },
                        label = {
                            Text("player 2")
                        },
                        modifier = modifier
                    )
                    if (homeUiState.playerOne.isNotEmpty() && homeUiState.playerTwo.isNotEmpty())
                    TextButton(onClick = {
                        goToDisplay()
                    removeDialog()}
                    ) {
                        Text(
                            "Start",
                            modifier = modifier)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    canGoBack: Boolean = true,
    navigateUp: () -> Unit = {},
    title: String = ""
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = Modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canGoBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    Modifier.clickable {
                        navigateUp()
                    }
                )

            }
        }


    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopParPreview() {
    ScreenTopBar(canGoBack = false)
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreePreview() {
    HomeScreen(
        scrollBehavior = null,
        canGoBack = false,
        vm = viewModel(factory = AppViewModelProvider.Factory),
        gamelist = emptyList(),
        loadGame = { },
        goToDisplay = {}
    )
}