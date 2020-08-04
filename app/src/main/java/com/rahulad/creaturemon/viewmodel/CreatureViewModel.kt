package com.rahulad.creaturemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahulad.creaturemon.model.*
import com.rahulad.creaturemon.model.room.RoomRepository

/**
 * Created by Rahul Lad on 7/26/2020.
 */


class CreatureViewModel(private val generator: CreatureGenerator = CreatureGenerator(),
                        private val repository: RoomRepository) : ViewModel() {


    private val creatureLivedata = MutableLiveData<Creature>()
    fun getCreatureLiveData(): LiveData<Creature> = creatureLivedata

    var name = ""
    var intelligence = 0
    var strength = 0
    var endurance = 0
    var drawable = 0

    lateinit var creature: Creature

    fun updateCreature(){
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name, drawable)
        creatureLivedata.postValue(creature)
    }

    fun attributeSelected(attributeType: AttributeType, position: Int){
        when (attributeType){
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE->
                endurance = AttributeStore.ENDURANCE[position].value

        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int){
        this.drawable = drawable
        updateCreature()
    }

    fun canSaveCreature(): Boolean{
        return intelligence != 0 && strength != 0 && endurance != 0 && name.isNotEmpty() && drawable != 0
    }

    fun saveCreature() : Boolean{
        return if (canSaveCreature()){
            repository.saveCreatures(creature)
            true
        } else {
            false
        }
    }
}