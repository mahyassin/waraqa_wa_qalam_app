package com.example.waraqawaqalam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kingdoms: List<Kingdom>,
    val type: String
)

data class Kingdom(
    val lutoch: Float,
    val dinari: Float,
    val girls: List<Girl>,
    val king: Girl

)

interface Girl {
    val p1Ate: Boolean
    val doubled: Boolean
}
