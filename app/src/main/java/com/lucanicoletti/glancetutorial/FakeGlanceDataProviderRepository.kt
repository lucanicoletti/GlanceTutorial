package com.lucanicoletti.glancetutorial

import android.content.Context
import android.content.res.Resources

class FakeGlanceDataProviderRepository(
    private val resources: Resources
) {

    companion object {
        private var instance: FakeGlanceDataProviderRepository? = null
        fun get(context: Context): FakeGlanceDataProviderRepository {
            if (instance == null) {
                instance = FakeGlanceDataProviderRepository(context.resources)
            }
            return instance!!
        }
    }

    fun getUsersName(): String {
        return resources.getString(R.string.greeting, "Luca")
    }

    fun getUsersNotes(): List<String> {
        return listOf(
            "Quick note",
            "A note about anything",
            "Last note"
        )
    }
}