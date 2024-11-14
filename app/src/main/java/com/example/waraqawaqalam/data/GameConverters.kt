package com.example.waraqawaqalam.data

import androidx.room.PrimaryKey

class GameConverters {

    fun Game.toKingdoms(): Kingdom {
        val girls = listOf<Girl>(
            Girl(this.girl1,this.girl1Double),
            Girl(this.girl2,this.girl2Double),
            Girl(this.girl3,this.girl3Double),
            Girl(this.girl4,this.girl4Double)
        )
        return Kingdom(
            gameId = this.gameId,
            kingdomId = this.kingdomId,
            lutoch = this.lutoch,
            dinari = this.dinari,
            girls = girls,
            king = Girl(this.king,this.kingDouble)
        )
    }
    fun Kingdom.toGame(): Game {
        return Game(
            gameId = this.gameId,
            kingdomId = this.kingdomId,
            lutoch = this.lutoch,
            dinari = this.dinari,
            girl1 = this.girls[0].p1Ate,
            girl2 = this.girls[1].p1Ate,
            girl3 = this.girls[2].p1Ate,
            girl4 = this.girls[3].p1Ate,
            girl1Double = this.girls[0].doubled,
            girl2Double = this.girls[1].doubled,
            girl3Double = this.girls[2].doubled,
            girl4Double = this.girls[3].doubled,
            king = this.king.p1Ate,
            kingDouble = this.king.doubled,
            type = ""
        )
    }
}
