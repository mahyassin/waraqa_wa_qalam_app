package com.example.waraqawaqalam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    suspend fun getAllKingdoms(): List<Game>

    @Query("SELECT * FROM GAME WHERE gameId = :gameId")
    fun getKingdom(gameId: Int): Flow<List<Game>>

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGame(game: Game)

    @Update
    suspend fun updateKingdom(game: Game)

    @Query("delete from game")
    suspend fun deleteAll()

    @Query("delete from game where gameId = :gameId")
    suspend fun deleteGame(gameId: Int)

}