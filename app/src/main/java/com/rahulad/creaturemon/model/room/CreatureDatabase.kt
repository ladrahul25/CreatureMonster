package com.rahulad.creaturemon.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulad.creaturemon.model.Creature

/**
 * Created by Rahul Lad on 7/25/2020.
 */
@Database(entities = [(Creature::class)], version = 1)
@TypeConverters(CreatureAttributesConverter::class)
abstract class CreatureDatabase : RoomDatabase() {
    abstract fun creatureDao(): CreatureDao
}