package com.lucanicoletti.glancetutorial

import android.content.Context
import android.content.res.Resources
import kotlinx.coroutines.delay

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

    suspend fun getUsersName(): String {
        delay(400)
        return resources.getString(R.string.app_name)
    }

    suspend fun getUsersReminders(): List<String> {
        delay(400)
        return listOf(
            "Plan Glance video",
            "Code Glance project",
            "Publish Glance video"
        )
    }
}