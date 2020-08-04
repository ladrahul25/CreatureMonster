package com.rahulad.creaturemon.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rahulad.creaturemon.model.Creature

/**
 * Created by Rahul Lad on 7/25/2020.
 */

@Dao
interface CreatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(creature: Creature)

    @Delete
    fun clearCreature(vararg creature: Creature)

    @Query("SELECT * from creature_table ORDER BY name ASC")
    fun getAllCreatures(): LiveData<List<Creature>>
}
