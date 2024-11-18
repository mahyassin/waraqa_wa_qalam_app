package com.example.waraqawaqalam.ui.loadGame

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.waraqawaqalam.data.Kingdom

class LoadingVM: ViewModel() {

    var games by mutableStateOf<List<Kingdom>>(emptyList())
        private set

    fun getGames(gameList: List<Kingdom>): List<List<Kingdom>> {

        val groupedList = gameList.groupBy { it.gameId }

        return groupedList.values.toList()



    }

}