package com.example.waraqawaqalam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllKingdoms(): Flow<List<Game>>

    @Query("SELECT * FROM GAME WHERE id = :id")
    fun getKingdom(id: Int): Flow<Game>

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGame(game: Game)
}