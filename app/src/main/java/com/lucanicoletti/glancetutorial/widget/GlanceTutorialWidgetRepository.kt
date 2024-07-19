package com.lucanicoletti.glancetutorial.widget

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.lucanicoletti.glancetutorial.db.dao.NotesDao
import com.lucanicoletti.glancetutorial.db.model.Note
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class GlanceTutorialWidgetRepository @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val notesDao: NotesDao
) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface GlanceTutorialWidgetRepositoryEntryPoint {
        fun widgetRepository(): GlanceTutorialWidgetRepository
    }

    companion object {
        fun get(applicationContext: Context): GlanceTutorialWidgetRepository {
            val widgetModelRepositoryEntryPoint: GlanceTutorialWidgetRepositoryEntryPoint =
                EntryPoints.get(
                    applicationContext,
                    GlanceTutorialWidgetRepositoryEntryPoint::class.java,
                )
            return widgetModelRepositoryEntryPoint.widgetRepository()
        }
    }

    suspend fun noteInserted() {
        MyAppWidget().updateAll(appContext)
    }

    suspend fun noteDeleted() {
        MyAppWidget().updateAll(appContext)
    }

    fun loadNotes(): Flow<List<Note>> {
        return notesDao.getNotes().distinctUntilChanged()
    }

}