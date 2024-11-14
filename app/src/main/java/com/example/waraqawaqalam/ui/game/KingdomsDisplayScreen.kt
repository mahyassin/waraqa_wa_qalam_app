package com.example.waraqawaqalam.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.waraqawaqalam.ui.navigation.NavigationDestination
import com.example.waraqawaqalam.ui.home.ScreenTopBar


object DisplayNavigation: NavigationDestination {
    override val route: String
        get() = "kingdom display"
    override val title: String
        get() = "the Kingdoms"

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KingdomDisplayScreen(
    players: List<String>,
    navigateUp: () -> Unit,
) {
    val player1 by rememberSaveable { mutableStateOf(players[0]) }
    val player2 by rememberSaveable { mutableStateOf(players[1]) }
    Scaffold(
        topBar = { ScreenTopBar(
            canGoBack = true,
            navigateUp = { navigateUp()},
            title = DisplayNavigation.title
        ) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(){
            ScoreBanner(
                modifier = Modifier.padding(innerPadding),
                player1,
                player2,
            )
            Divider(Modifier.padding(vertical = 8.dp))

        }
    }
}

@Composable
fun ScoreBanner(
    modifier: Modifier,
    player1: String,
    player2: String
) {
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
            Text("100")
        }
        Text("400",modifier)
        Column(
            modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(player2)
                Text("100")
        }
    }
}

@Composable
fun KingdomCard() {

    OutlinedCard() {
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.SpaceBetween
        ) {
            Text("100",Modifier.padding(8.dp))
            Text("100",Modifier.padding(8.dp))
            Text("100",Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun KingdomCardPreview() {
    KingdomCard()
}

@Preview(showBackground = true)
@Composable
fun ScoreBannerPreview() {
   ScoreBanner(
       Modifier,
       player1 = "mahmoud",
       player2 = "ahmad"
   )
}
@Preview
@Composable
fun KingdomDisplayPreview() {
    KingdomDisplayScreen(
        players = listOf("player)ne","playerTwo"),
        navigateUp = {}
    )
}