package com.sample.musicapp.mvp

import kotlin.reflect.KProperty

// https://stackoverflow.com/questions/42397686/how-to-set-lateinit-kotlin-property-to-null/42398736#42398736

/**
 * Manages the list of references to reset.
 */
class ResettableReferences {
    private val resettables = mutableListOf<Resettable<*, *>>()

    fun register(resettable: Resettable<*, *>) {
        resettables.add(resettable)
    }

    fun reset() {
        resettables.forEach(Resettable<*, *>::reset)
    }
}

/**
 * Kotlin delegate to automatically clear (nullify) strong references.
 */
class Resettable<in R, T : Any>(holder: ResettableReferences) {
    init {
        holder.register(this)
    }

    private var value: T? = null

    operator fun getValue(thisRef: R, property: KProperty<*>): T =
        value ?: throw UninitializedPropertyAccessException(property.name)

    operator fun setValue(thisRef: R, property: KProperty<*>, t: T) {
        value = t
    }

    fun reset() {
        value = null
    }
}