package com.example.waraqawaqalam.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.waraqawaqalam.WaraqaWaQalamApplication
import com.example.waraqawaqalam.data.GameRepository
import com.example.waraqawaqalam.ui.scoreCulculator.ScoreCulculatorVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.waraqawaqalam.data.Games
import com.example.waraqawaqalam.data.playerOne
import com.example.waraqawaqalam.data.playerTwo
import com.example.waraqawaqalam.ui.game.KingdomDisplayVM
import com.example.waraqawaqalam.ui.navigation.NavigatorVM

class HomeScreenVM(val gameRepository: GameRepository): ViewModel() {


    private var _homeUiState =
        MutableStateFlow(HomeUiState(playerOne = "", playerTwo = "", playersNamed = false))
    val homeUiState = _homeUiState.asStateFlow()


    fun popAddName() {
        _homeUiState.update { it.copy(playersNamed = !_homeUiState.value.playersNamed) }
    }

    fun updatePlayerOne(newName: String) {
        _homeUiState.update { it.copy(playerOne = newName) }
    }

    fun updatePlayerTwo(newName: String) {
        _homeUiState.update { it.copy(playerTwo = newName) }
    }

    fun submitNames() {
        playerOne = _homeUiState.value.playerOne
        playerTwo = _homeUiState.value.playerTwo
    }

}


data class HomeUiState(
    val playerOne: String,
    val playerTwo: String,
    val playersNamed: Boolean,

)


