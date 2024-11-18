package com.example.waraqawaqalam.ui.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.example.waraqawaqalam.data.GameRepository
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.gameId
import com.example.waraqawaqalam.data.toKingdoms
import com.example.waraqawaqalam.data.totalScore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.map

class KingdomDisplayVM(val repository: GameRepository) : ViewModel() {
    private var _displayUiState = MutableStateFlow(DisplayUiState())


   var games: StateFlow<List<Kingdom>> = repository.getKingdom(gameId)
            .map { it.map { it.toKingdoms() } }.stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = emptyList()
            )



    val displayUiState = _displayUiState.asStateFlow()

    // culculate the total score of the players

    fun whoIsWinner(kingdoms: List<Kingdom>) {

        // decide who is the winner after 8 kingdoms
        if (kingdoms.size >= 8) {
            val p1Score = totalScore
            val p2Score = -500 * kingdoms.size - p1Score
            if (p1Score > p2Score) _displayUiState.update { it.copy(p1Win = true) }
            else _displayUiState.update { it.copy(p1Win = false) }
        }
    }


}

data class DisplayUiState(
    var totalScore: Int = 0,
    var p1Win: Boolean? = null,
)