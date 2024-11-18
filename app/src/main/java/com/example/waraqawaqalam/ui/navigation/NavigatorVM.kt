package com.example.waraqawaqalam.ui.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.waraqawaqalam.data.GameRepository
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.gameId
import com.example.waraqawaqalam.data.playerOne
import com.example.waraqawaqalam.data.playerTwo
import com.example.waraqawaqalam.data.toKingdoms
import com.example.waraqawaqalam.data.totalScore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigatorVM(val gameRepository: GameRepository): ViewModel() {


    init {
        getKingdoms()
    }
    var gamesList by mutableStateOf<List<Kingdom>>(listOf())

    private var _editKingdom = MutableStateFlow(Kingdom())
    val editedKingdom = _editKingdom.asStateFlow()


    fun getKingdoms() {
        viewModelScope.launch {
            gamesList = gameRepository.getAll().map { it.toKingdoms() }
        }
    }
    fun editKingdom(kingdom: Kingdom) {
        _editKingdom.value = kingdom
    }

    fun updateKingdom(kingdom: Kingdom) {
        viewModelScope.launch {
           gameRepository.updateKingdom(kingdom)
        }
    }

    fun addGame(gameList: List<Kingdom>) {
        totalScore = 0
        playerOne = ""
        playerTwo = ""
        if (gameList.isNotEmpty()) {
           gameList.forEach {
              if (it.gameId == gameId) gameId++
           }
        }
    }
}
