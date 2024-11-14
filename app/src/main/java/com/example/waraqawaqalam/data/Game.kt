package com.example.waraqawaqalam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gameId: Int = 0,
    val kingdomId: Int,
    val lutoch: Float,
    val dinari: Float,
    val girl1: Boolean,
    val girl2: Boolean,
    val girl3: Boolean,
    val girl4: Boolean,
    val girl1Double: Boolean,
    val girl2Double: Boolean,
    val girl3Double: Boolean,
    val girl4Double: Boolean,
    val king: Boolean,
    val kingDouble: Boolean,
    val type: String,
)
data class Kingdom(
    val gameId: Int,
    val kingdomId: Int,
    val lutoch: Float,
    val dinari: Float,
    val girls: List<Girl>,
    val king: Girl

)

data class Girl (
    val p1Ate: Boolean,
    val doubled: Boolean
)

