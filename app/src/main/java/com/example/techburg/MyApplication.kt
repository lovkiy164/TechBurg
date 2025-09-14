package com.example.techburg

import android.app.Application
import android.content.Context
import androidx.room.Room

class MyApplication : Application(){
    private var _appDatabase: AppDataBase? = null
    val appDatabase get() = requireNotNull(_appDatabase)
    override fun onCreate() {
        super.onCreate()
        _appDatabase = Room
            .databaseBuilder(
                this,
                AppDataBase::class.java,
                "app_database"
            )
            .allowMainThreadQueries()
            .build()
    }
}

val Context.appDatabase: AppDataBase
    get() = when{
        this is MyApplication -> appDatabase
        else -> applicationContext.appDatabase
    }