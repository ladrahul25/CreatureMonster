package com.rahulad.creaturemon.model

import androidx.lifecycle.LiveData

/**
 * Created by Rahul Lad on 7/25/2020.
 */

interface CreatureRepository {
    fun saveCreatures(creature: Creature)
    fun getAllCreatures(): LiveData<List<Creature>>
    fun clearAllCreatures()
}