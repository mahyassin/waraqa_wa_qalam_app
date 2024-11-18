package com.example.waraqawaqalam.data

import androidx.room.PrimaryKey


    fun Game.toKingdoms(): Kingdom {
        val girls = listOf<Girl>(
            Girl(this.girl1, this.girl1Double),
            Girl(this.girl2, this.girl2Double),
            Girl(this.girl3, this.girl3Double),
            Girl(this.girl4, this.girl4Double),
            Girl(this.girl5, this.girl5Double)
        )
        return Kingdom(
            gameId = this.gameId,
            kingdomId = this.id,
            lutoch = this.lutoch,
            dinari = this.dinari,
            girls = girls,
            score = this.score,
            playerOneName = this.playerOneName,
            playerTwoName = this.playerTwoName
        )
    }
    fun Kingdom.toGame(): Game {
        return Game(
            id = this.kingdomId,
            gameId = this.gameId,
            kingdomId = this.kingdomId,
            lutoch = this.lutoch,
            dinari = this.dinari,
            girl1 = this.girls[0].p1Ate!!,
            girl2 = this.girls[1].p1Ate!!,
            girl3 = this.girls[2].p1Ate!!,
            girl4 = this.girls[3].p1Ate!!,
            girl5 = this.girls[4].p1Ate!!,
            girl1Double = this.girls[0].doubled,
            girl2Double = this.girls[1].doubled,
            girl3Double = this.girls[2].doubled,
            girl4Double = this.girls[3].doubled,
            girl5Double = this.girls[4].doubled,
            type = "",
            score = this.score,
            playerOneName = this.playerOneName,
            playerTwoName = this.playerTwoName
        )
    }

