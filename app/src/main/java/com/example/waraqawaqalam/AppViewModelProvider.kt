package com.example.waraqawaqalam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.waraqawaqalam.ui.game.KingdomDisplayVM
import com.example.waraqawaqalam.ui.home.HomeScreenVM
import com.example.waraqawaqalam.ui.navigation.NavigatorVM
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorVM


object AppViewModelProvider {


    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {

            HomeScreenVM(warqaWaQalamApplication().container.gameRepository)
        }
        initializer {

            ScoreCulculatorVM(warqaWaQalamApplication().container.gameRepository)
        }
        initializer {

            NavigatorVM(warqaWaQalamApplication().container.gameRepository)
        }
        initializer {

            KingdomDisplayVM(warqaWaQalamApplication().container.gameRepository)
        }


    }
}

fun CreationExtras.warqaWaQalamApplication(): WaraqaWaQalamApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WaraqaWaQalamApplication)
