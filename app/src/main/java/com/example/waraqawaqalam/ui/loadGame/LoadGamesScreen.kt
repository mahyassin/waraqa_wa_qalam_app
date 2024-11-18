package com.example.waraqawaqalam.ui.loadGame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    navigateUp: () -> Unit
    ) {



    Scaffold(
        topBar = {
            ScreenTopBar(
                title = LoadDestination.title,
                canGoBack = true,
                navigateUp = { navigateUp()}
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Divider()
            LazyColumn {
                items(loadingVM.getGames(gamesList)) {
                    GameCard(it) { loadGame (it[0].gameId)}
                }
            }
        }
    }
}

@Composable
fun GameCard(
    gamesList: List<Kingdom>,
    loadGame:() -> Unit
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
                    Text("${gamesList.size}")
                }
                              },
            leadingContent = {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Localized description",
                )
            }
        )
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
        loadGame = {}
    )
}

@Preview
@Composable
fun LoadGameScreenPreview() {
    LoadGameScreen(
        emptyList(),
        loadGame = { },
        navigateUp = {}
    )
}