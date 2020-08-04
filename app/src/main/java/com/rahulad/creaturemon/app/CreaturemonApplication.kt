package com.rahulad.creaturemon.app

import android.app.Application
import androidx.room.Room
import com.rahulad.creaturemon.model.room.CreatureDatabase

/**
 * Created by Rahul Lad on 7/25/2020.
 */
class CreaturemonApplication : Application() {

    companion object {
        lateinit var database: CreatureDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, CreatureDatabase::class.java, "creature_database")
            .build()
    }
}