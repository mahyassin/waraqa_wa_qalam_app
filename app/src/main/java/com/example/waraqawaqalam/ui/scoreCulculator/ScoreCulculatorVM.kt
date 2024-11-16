package com.example.waraqawaqalam.ui.scoreCulculator

import androidx.lifecycle.ViewModel
import com.example.waraqawaqalam.data.GameRepository
import com.example.waraqawaqalam.data.Girl
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.kingdoms
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ScoreCulculatorVM(val repository: GameRepository): ViewModel() {
    private var _kingdom = MutableStateFlow(Kingdom())
    val kingdom = _kingdom.asStateFlow()

    private var _uiState = MutableStateFlow(ScoreUistate())
    val uistate = _uiState.asStateFlow()

    fun updateLutoch( newValue: Float) {
        _kingdom.update { it.copy(lutoch = newValue) }
    }

    fun updateDinari(newValue: Float) {
        _kingdom.update { it.copy(dinari = newValue) }
    }
    fun expand() {
        _uiState.update { it.copy(
            expanded = !_uiState.value.expanded
        ) }
    }
    suspend fun insertKingdom(kingdom: Kingdom) {
        repository.saveKingdom(kingdom)
    }
    fun decideWinner() {
        if (kingdoms.size >= 8){
            _kingdom.update { it.copy(gameFinished = true) }
        }
    }

    fun scoreCulolator(kingdom: Kingdom) {

        val lotoch = kingdom.lutoch.toInt() * -15
        val dinari = kingdom.dinari.toInt() * -10
        val girls = girlSepearator(kingdom)
        val king = kingCuculator(kingdom.girls[4])
        kingdom.score = lotoch + dinari + girls + king
    }

    private fun kingCuculator(girl: Girl): Int {
        return  when {
            girl.p1Ate == true && girl.doubled -> -150
            girl.p1Ate == false && girl.doubled -> 75
            girl.p1Ate == false && !girl.doubled -> 0
            else -> -75
        }
    }

    fun girlCulculator(girl: Girl): Int {
        return  when {
            girl.p1Ate == true && girl.doubled -> -50
            girl.p1Ate == false && girl.doubled -> 25
            girl.p1Ate == false && !girl.doubled -> 0
            else -> -25
        }
    }
    fun girlSepearator(kingdom: Kingdom): Int {
        var value = 0
        kingdom.girls.forEachIndexed { i , girl ->
            if (i!=4) value += girlCulculator(girl)
        }
        return value
    }

    fun updateGirl(index: Int, p1ate: Boolean) {
        var tempGirls = kingdom.value.girls
        tempGirls[index].p1Ate = p1ate

        _kingdom.update { it.copy(girls = tempGirls) }

        kingdomUpdater()

    }

    fun doubled(index: Int) {
        var tempGirls = kingdom.value.girls
        tempGirls[index].doubled = !tempGirls[index].doubled
        _kingdom.update { it.copy(girls = tempGirls) }
        kingdomUpdater()
    }

    private fun kingdomUpdater() {
        _kingdom.update { it.copy(updater = !_kingdom.value.updater) }
    }


}
data class ScoreUistate(
    val expanded: Boolean = true,
    val girlsChosen: Boolean = false,
)
