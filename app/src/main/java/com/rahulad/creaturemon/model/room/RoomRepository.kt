package com.rahulad.creaturemon.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.rahulad.creaturemon.app.CreaturemonApplication
import com.rahulad.creaturemon.model.Creature
import com.rahulad.creaturemon.model.CreatureRepository

/**
 * Created by Rahul Lad on 7/25/2020.
 */
class RoomRepository : CreatureRepository {

    private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()
    private val allCreatures: LiveData<List<Creature>>

    init {
        allCreatures = creatureDao.getAllCreatures()
    }

    override fun getAllCreatures() = allCreatures

    override fun clearAllCreatures() {
        val creatureArray = allCreatures.value?.toTypedArray()
        if (creatureArray != null) {
            DeleteAsyncTask(creatureDao).execute(*creatureArray)
        }
    }

    private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
        override fun doInBackground(vararg params: Creature): Void? {
            dao.insert(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
        override fun doInBackground(vararg params: Creature): Void? {
            dao.clearCreature(*params)
            return null
        }
    }

    override fun saveCreatures(creature: Creature) {
        InsertAsyncTask(creatureDao).execute(creature)
    }
}