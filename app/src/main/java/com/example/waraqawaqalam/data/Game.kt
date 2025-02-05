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
    val girl5: Boolean,
    val girl1Double: Boolean,
    val girl2Double: Boolean,
    val girl3Double: Boolean,
    val girl4Double: Boolean,
    val girl5Double: Boolean,
    val playerOneName: String,
    val playerTwoName: String,
    val score: Int,
    val type: String,
)
data class Kingdom(
    val gameFinished: Boolean = false,
    var gameId: Int = 0,
    var score: Int = 0,
    val kingdomId: Int = 0,
    val lutoch: Float = 0f,
    val dinari: Float = 0f,
    var playerOneName: String = "",
    var playerTwoName: String = "",
    val girls: List<Girl> = listOf<Girl>(
        Girl(null,false),
        Girl(null,false),
        Girl(null,false),
        Girl(null,false),
        Girl(null,false),
        ),
    var updater: Boolean = false,

    )

data class Girl (
    var p1Ate: Boolean?,
    var doubled: Boolean
)
data class Games(
    var gameLists: List<Game> = listOf<Game>()
)


var gameId = 0
var totalScore = 0
var playerOne = "playerOne"
var playerTwo = "playerTwo"
var addNotEdit = true

