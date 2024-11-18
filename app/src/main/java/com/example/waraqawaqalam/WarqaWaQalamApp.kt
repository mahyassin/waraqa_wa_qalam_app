package com.example.waraqawaqalam

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.waraqawaqalam.ui.navigation.Navigator
import com.example.waraqawaqalam.ui.theme.WaraqaWaQalamTheme

@Composable
fun WaraqaWaQalamApp() {

        Navigator()
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    WaraqaWaQalamTheme {

    }
}