package com.rahulad.creaturemon.model.room

import androidx.room.TypeConverter
import com.rahulad.creaturemon.model.CreatureAttributes
import java.util.*

/**
 * Created by Rahul Lad on 7/25/2020.
 */
class CreatureAttributesConverter {
    @TypeConverter
    fun fromCreatureAttributes(attributes: CreatureAttributes?): String? {
        if (attributes != null) {
            return String.format(Locale.US, "%d,%d,%d", attributes.intelligence, attributes.strength, attributes.endurance)
        }
        return null
    }

    @TypeConverter
    fun toCreatureAttributes(value: String?): CreatureAttributes? {
        if (value != null) {
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return CreatureAttributes(
                java.lang.Integer.parseInt(pieces[0]),
                java.lang.Integer.parseInt(pieces[1]),
                java.lang.Integer.parseInt(pieces[2]))
        }
        return null
    }
}