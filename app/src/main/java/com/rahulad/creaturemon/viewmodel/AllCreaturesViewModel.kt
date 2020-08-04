package com.rahulad.creaturemon.viewmodel
import androidx.lifecycle.ViewModel
import com.rahulad.creaturemon.model.CreatureRepository

/**
 * Created by Rahul Lad on 7/29/2020.
 */
class AllCreaturesViewModel(private val repository: CreatureRepository) : ViewModel() {

    private val allCreatureLiveData = repository.getAllCreatures()

    fun getAllCreaturesLiveData() = allCreatureLiveData

    fun clearAllCreatures() = repository.clearAllCreatures()
}