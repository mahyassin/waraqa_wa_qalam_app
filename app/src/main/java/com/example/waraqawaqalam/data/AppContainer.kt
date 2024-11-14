package com.example.waraqawaqalam.data

import android.content.Context

class AppContainer(
    context: Context
) {
    val gameRepository by lazy {
        GameRepository(GamesDataBase.getDatabase(context).gameDao())
    }
}