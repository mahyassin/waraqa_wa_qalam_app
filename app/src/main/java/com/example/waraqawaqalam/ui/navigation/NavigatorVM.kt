package com.example.waraqawaqalam.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigatorVM: ViewModel() {
    private var _savedIfo = MutableStateFlow(SavedIfo(
        playerOneName = "",
        playerTwoName = "",
        gameId = 0
    ))
    fun updateNames(p1: String, p2: String) {
        _savedIfo.update { it.copy(
            playerOneName = p1,
            playerTwoName = p2
        ) }
    }

    val savedIfo = _savedIfo.asStateFlow()
}
data class SavedIfo(
    val playerOneName: String,
    val playerTwoName: String,
    val gameId: Int,
)