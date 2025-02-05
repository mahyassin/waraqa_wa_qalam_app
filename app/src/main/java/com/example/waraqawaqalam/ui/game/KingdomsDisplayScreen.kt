package com.example.waraqawaqalam.ui.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waraqawaqalam.AppViewModelProvider
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.addNotEdit
import com.example.waraqawaqalam.data.totalScore
import com.example.waraqawaqalam.ui.navigation.NavigationDestination
import com.example.waraqawaqalam.ui.home.ScreenTopBar
import kotlin.math.abs


object DisplayNavigation: NavigationDestination {
    override val route: String
        get() = "kingdom display"
    override val title: String
        get() = "the Kingdoms"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KingdomDisplayScreen(
    addKingdom: () -> Unit,
    navigateUp: () -> Unit,
    vm: KingdomDisplayVM = viewModel(factory = AppViewModelProvider.Factory),
    player1: String,
    player2: String,
    gotoStartScreen: () -> Unit,
    editKingdom: (Kingdom) -> Unit,

    ) {
    val uiState by vm.displayUiState.collectAsState()
    val currentKingdoms by vm.games.collectAsState()
    var winner: String = when (uiState.p1Win) {
        true -> player1
        false -> player2
        null -> ""
    }
    vm.whoIsWinner(currentKingdoms)

    Scaffold(
        topBar = { ScreenTopBar(
            canGoBack = true,
            navigateUp = { navigateUp()},
            title = DisplayNavigation.title
        ) },
        floatingActionButton = {
            FloatingActionButton(onClick = { addKingdom() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->

        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
            ScoreBanner(
                modifier = Modifier.padding(innerPadding),
                player1,
                player2,
                uiState,
                kingdoms = currentKingdoms,
            )
                Divider(Modifier.padding(vertical = 8.dp))
                LazyColumn {
                    items(currentKingdoms) { kingdom ->
                        KingdomCard(kingdom, Modifier.clickable{
                            addNotEdit = false
                            editKingdom(kingdom)
                        })
                    }
                }
                if (uiState.p1Win != null){
                    Text(
                        text = "winner is $winner",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            WinnerDialog(
                displayUiState = uiState,
                winner = winner,
                gotoStartScreen = gotoStartScreen,
            )
        }
    }
}

@Composable
fun WinnerDialog(
    displayUiState: DisplayUiState,
    winner: String,
    gotoStartScreen: () -> Unit,
) {
    if (displayUiState.p1Win != null){
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {

            Dialog(onDismissRequest = {}) {
                Card {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(" the winner is $winner")
                        TextButton(onClick = { gotoStartScreen() }) { Text("OK") }
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreBanner(
    modifier: Modifier,
    player1: String,
    player2: String,
    uiState: DisplayUiState,
    kingdoms: List<Kingdom>
) {
    val p2Score = -500* kingdoms.size - totalScore
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(player1)
            Text("${totalScore} ")
        }
        Text("${abs(p2Score - totalScore)}",modifier)
        Column(
            modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(player2)
                Text("$p2Score")
        }
    }
}

@Composable
fun KingdomCard(kingdom: Kingdom,modifier: Modifier = Modifier) {

    OutlinedCard(modifier.padding(4.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            Arrangement.SpaceBetween
        ) {
            Text("${kingdom.score}",Modifier.padding(8.dp))
            Text("${abs((- 500 - kingdom.score) -kingdom.score)}",Modifier.padding(8.dp))
            Text("${(- 500 - kingdom.score)}",Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun KingdomCardPreview() {
    KingdomCard(Kingdom(
        playerOneName = "",
        playerTwoName = "",

    ))
}

@Preview
@Composable
fun WinnerDialogPreview() {
    WinnerDialog(
        displayUiState = DisplayUiState(),
        winner = "me"
    ){}
}

@Preview(showBackground = true)
@Composable
fun ScoreBannerPreview() {
   ScoreBanner(
       Modifier,
       player1 = "mahmoud",
       player2 = "ahmad",
       uiState = DisplayUiState(),
       kingdoms = emptyList()
   )
}
@Preview
@Composable
fun KingdomDisplayPreview() {
    KingdomDisplayScreen(
        {},
        navigateUp = {},
        player1 = "ahmad",
        player2 = "adel",
        gotoStartScreen = { },
        editKingdom = {  }
    )
}