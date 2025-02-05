package com.example.waraqawaqalam.ui.loadGame

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.waraqawaqalam.data.Kingdom

class LoadingVM: ViewModel() {


    fun getGames(gameList: List<Kingdom>): MutableList<List<Kingdom>> {

        val groupedList = gameList.groupBy { it.gameId }

        return groupedList.values.toMutableList()



    }

}