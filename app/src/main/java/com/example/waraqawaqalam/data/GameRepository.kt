package com.example.waraqawaqalam.data

class GameRepository(val gameDao: GameDao) {

    fun getAll() = gameDao.getAllKingdoms()

    fun getGame(id: Int) = gameDao.getKingdom(id)

}