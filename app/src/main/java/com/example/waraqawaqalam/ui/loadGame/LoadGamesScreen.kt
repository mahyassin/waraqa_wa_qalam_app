package com.example.waraqawaqalam.ui.loadGame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.gameId
import com.example.waraqawaqalam.data.totalScore
import com.example.waraqawaqalam.ui.home.ScreenTopBar
import com.example.waraqawaqalam.ui.navigation.NavigationDestination


object LoadDestination: NavigationDestination {
    override val route: String
        get() = "Load"
    override val title: String
        get() = "Load Game"

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadGameScreen(
    gamesList: List<Kingdom>,
    loadingVM: LoadingVM = viewModel(),
    loadGame: (Int) -> Unit,
    navigateUp: () -> Unit,
    delelteGame: (Int) -> Unit,
    deleteAll: () -> Unit,
    ) {

    var displayedGames = loadingVM.getGames(gamesList)
    var screenRecomposer by remember {  mutableStateOf(true) }
    Scaffold(
        topBar = {
            ScreenTopBar(
                title = LoadDestination.title,
                canGoBack = true,
                navigateUp = { navigateUp()}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            screenRecomposer
            Divider()
            LazyColumn(Modifier.weight(12f)) {
                items(displayedGames) {
                    GameCard(
                        it,
                        deleteGame = {
                            delelteGame(it[0].gameId)
                            displayedGames.remove(it)
                            screenRecomposer = !screenRecomposer
                        }
                    ) { loadGame (it[0].gameId)}
                }
            }
            DeleteButton(
                Modifier.weight(2f),
                deleteAll = { deleteAll()
                    displayedGames = mutableListOf()
                    screenRecomposer = !screenRecomposer
                }
            )

        }
    }
}
@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    deleteAll: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Divider(
            thickness = 2.dp,
        )
        OutlinedButton(
            onClick = { deleteAll()},
            Modifier.padding(8.dp)
        ) {
            Text("Delete")
        }
    }
}

@Composable
fun GameCard(
    gamesList: List<Kingdom>,
    deleteGame: () -> Unit,
    loadGame:() -> Unit,
) {
    fun score(p1: Boolean): Int{
        var p1Score = 0
        gamesList.forEach {
            p1Score += it.score
        }
        if (p1)return p1Score  else return -500 * gamesList.size -p1Score
    }
    Column(
        modifier = Modifier.clickable {
            loadGame()
            totalScore = score(true)
        }
    ){
        ListItem(
            headlineContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Column() {
                        Text("${gamesList[0].playerOneName}   Score: ${score(true)}")
                        Text("${gamesList[0].playerTwoName}   Score: ${score(false)}")
                    }
                    TextButton(onClick = {deleteGame()}) {
                        Text(
                            "Delete",
                            color = Color(0xff772222)) }
                }
                              },
            leadingContent = {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Localized description",
                )
            }
        )
        Divider()
    }
}

@Preview
@Composable
fun GameCardPreview() {
    GameCard(
        gamesList = listOf(
            Kingdom(
                playerOneName = "asim",
                playerTwoName = "adel",

                )
        ),
        loadGame = {},
        deleteGame = {  }
    )
}

@Preview
@Composable
fun LoadGameScreenPreview() {
    LoadGameScreen(
        emptyList(),
        loadGame = { },
        navigateUp = {},
        deleteAll = { },
      
        delelteGame = {  }
    )
}