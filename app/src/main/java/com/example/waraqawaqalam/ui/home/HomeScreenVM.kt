package com.example.waraqawaqalam.ui.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.waraqawaqalam.WaraqaWaQalamApplication
import com.example.waraqawaqalam.data.Game
import com.example.waraqawaqalam.data.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenVM(gameRepository: GameRepository): ViewModel() {

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

}
data class HomeUiState(
    val gameLists: List<Game> = listOf<Game>(),
    val playerOne: String,
    val playerTwo: String,
    val playersNamed: Boolean,
)


object AppViewModelProvider {


    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as WaraqaWaQalamApplication)
            HomeScreenVM(application.container.gameRepository)
        }
    }
}

