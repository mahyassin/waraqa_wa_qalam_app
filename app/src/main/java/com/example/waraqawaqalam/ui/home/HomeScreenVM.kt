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
import com.example.waraqawaqalam.ui.game.KingdomDisplayVM

class HomeScreenVM(val gameRepository: GameRepository): ViewModel() {


    private var _homeUiState =
        MutableStateFlow(HomeUiState(playerOne = "", playerTwo = "", playersNamed = false))
    val homeUiState = _homeUiState.asStateFlow()

    val gamesList: StateFlow<Games> = gameRepository.getAll().map {
        Games(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Games()
    )

    fun addGame() {
        gamesList.value.gameLists.forEach {
            while (it.gameId == _homeUiState.value.gameId) {
                _homeUiState.value.gameId++
            }
        }
    }
    fun popAddName() {
        _homeUiState.update { it.copy(playersNamed = !_homeUiState.value.playersNamed) }
    }

    fun updatePlayerOne(newName: String) {
        _homeUiState.update { it.copy(playerOne = newName) }
    }

    fun updatePlayerTwo(newName: String) {
        _homeUiState.update { it.copy(playerTwo = newName) }
    }
    fun getGames() {

    }

}

data class HomeUiState(
    val playerOne: String,
    val playerTwo: String,
    val playersNamed: Boolean,
    var gameId: Int = 0,
)


object AppViewModelProvider {


    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {

            HomeScreenVM(warqaWaQalamApplication().container.gameRepository)
        }
        initializer {

            ScoreCulculatorVM(warqaWaQalamApplication().container.gameRepository)
        }
        initializer {
            KingdomDisplayVM( this.createSavedStateHandle())
        }
    }
}

fun CreationExtras.warqaWaQalamApplication(): WaraqaWaQalamApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WaraqaWaQalamApplication)

