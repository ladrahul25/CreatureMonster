package com.rahulad.creaturemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulad.creaturemon.model.CreatureGenerator
import com.rahulad.creaturemon.model.room.RoomRepository

/**
 * Created by Rahul Lad on 7/31/2020.
 */
class CreatureViewModelFactory (private val generator: CreatureGenerator, private val repository: RoomRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreatureViewModel::class.java)) {
            return CreatureViewModel(generator, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}