package com.ozaltun.marvel

import android.app.Application
import com.ozaltun.marvel.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelApp)
            modules(
                listOf(
                    marvelModule
                )
            )
        }
    }
}