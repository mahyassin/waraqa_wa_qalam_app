package com.example.waraqawaqalam.data

import com.example.waraqawaqalam.data.GameConverters.Companion.toGame
import kotlinx.coroutines.flow.Flow

class GameRepository(val gameDao: GameDao) {

    fun getAll(): Flow<List<Game>> = gameDao.getAllKingdoms()

    fun getGame(id: Int) = gameDao.getKingdom(id)

    suspend fun saveKingdom(kingdom: Kingdom) {
        return gameDao.saveGame(kingdom.toGame())
    }

}