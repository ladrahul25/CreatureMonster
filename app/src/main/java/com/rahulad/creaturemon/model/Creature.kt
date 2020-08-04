package com.rahulad.creaturemon.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rahul Lad on 7/25/2020.
 */

@Entity(tableName = "creature_table")
data class Creature(
    val attributes: CreatureAttributes = CreatureAttributes(),
    val hitPoints: Int = 0,
    @PrimaryKey @NonNull val name : String,
    val drawables: Int = 0
)