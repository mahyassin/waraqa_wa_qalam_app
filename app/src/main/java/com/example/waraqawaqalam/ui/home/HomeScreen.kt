package com.example.waraqawaqalam.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.waraqawaqalam.navigation.NavigationDestination

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
    canGoBack: Boolean
) {

    Scaffold(
        topBar = { HomeScreenTopBar(
            scrollBehavior = scrollBehavior,
            canGoBack = canGoBack,

        ) }
    ) {
        innerPadding ->
        StartScreen(Modifier.padding(innerPadding))
    }
}

@Composable
fun StartScreen(modifier: Modifier) {

    Column {
        Button(onClick = {}) {
            Text("Create a new game")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    canGoBack: Boolean = true,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(HomeDestinatoin.title) },
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
    HomeScreenTopBar(canGoBack = false)
}