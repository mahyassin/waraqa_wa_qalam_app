package com.example.waraqawaqalam.ui.game

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class KingdomDisplayVM: ViewModel() {
    private var _displayUiState = MutableStateFlow(DisplayUiState())
    val displayUiState = _displayUiState.asStateFlow()
}

data class DisplayUiState(
    val playerOneName: String = "",
    val playerTwoName: String = "",
)