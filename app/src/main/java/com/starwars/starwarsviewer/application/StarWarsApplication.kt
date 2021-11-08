package com.starwars.starwarsviewer.application

import android.app.Application
import com.starwars.starwarsviewer.di.AppComponent
import com.starwars.starwarsviewer.di.AppModule
import com.starwars.starwarsviewer.di.DaggerAppComponent

class StarWarsApplication: Application() {
    companion object {
        lateinit var starWarsComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        starWarsComponent = initDagger(this)
    }

    private fun initDagger(app: StarWarsApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}