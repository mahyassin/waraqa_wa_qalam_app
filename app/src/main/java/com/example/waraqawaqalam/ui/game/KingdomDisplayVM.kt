package com.example.waraqawaqalam.ui.game

import androidx.lifecycle.ViewModel
import com.example.waraqawaqalam.data.kingdoms
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.SavedStateHandle

class KingdomDisplayVM(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _displayUiState = MutableStateFlow(DisplayUiState())

    init {
        totalScoreCulcolator()
    }
    val arg1: String? = savedStateHandle["arg1"]

    val displayUiState = _displayUiState.asStateFlow()

    fun totalScoreCulcolator() {
        kingdoms.forEach {
            _displayUiState.value.totalScore += it.score
        }
        if (kingdoms.size >= 8) {
            val p1Score = _displayUiState.value.totalScore
            val p2Score = -500 * kingdoms.size -_displayUiState.value.totalScore
            if (p1Score > p2Score) _displayUiState.update { it.copy(p1Win = true) }
            else _displayUiState.update { it.copy(p1Win = false) }
        }


    }


}

data class DisplayUiState(
    var totalScore: Int = 0,
    var p1Win: Boolean? = null,
)