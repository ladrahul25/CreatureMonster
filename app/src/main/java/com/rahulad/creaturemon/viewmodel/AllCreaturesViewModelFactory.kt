package com.rahulad.creaturemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulad.creaturemon.model.CreatureRepository

/**
 * Created by Rahul Lad on 7/31/2020.
 */
class AllCreaturesViewModelFactory(private val repository: CreatureRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllCreaturesViewModel::class.java)) {
            return AllCreaturesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}