package com.example.waraqawaqalam.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllKingdoms()

    @Query("SELECT * FROM GAME WHERE id = :id")
    fun getKingdom(id: Int)
}