package com.example.waraqawaqalam

import android.app.Application
import com.example.waraqawaqalam.data.AppContainer

class WaraqaWaQalamApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}