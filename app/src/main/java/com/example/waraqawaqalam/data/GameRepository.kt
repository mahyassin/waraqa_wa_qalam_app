package com.example.waraqawaqalam.data

import kotlinx.coroutines.flow.Flow

class GameRepository(val gameDao: GameDao) {

    suspend fun getAll(): List<Game> = gameDao.getAllKingdoms()


    fun getKingdom(gameId: Int): Flow<List<Game>> = gameDao.getKingdom(gameId)

    suspend fun saveKingdom(kingdom: Kingdom) {
        return gameDao.saveGame(kingdom.toGame())
    }

    suspend fun updateKingdom(kingdom: Kingdom) {
        gameDao.updateKingdom(kingdom.toGame())
    }

    suspend fun deleteAll() {
        gameDao.deleteAll()
    }

    suspend fun deleteGame(gameId: Int) {
        gameDao.deleteGame(gameId)
    }
}