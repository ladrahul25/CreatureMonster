package com.rahulad.creaturemon.model

/**
 * Created by Rahul Lad on 7/25/2020.
 */
data class AttributeValue(val name: String = "", val value: Int = 0) {
    override fun toString() = "$name: $value"
}