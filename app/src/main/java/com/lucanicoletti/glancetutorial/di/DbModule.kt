package com.lucanicoletti.glancetutorial.di

import android.content.Context
import androidx.room.Room
import com.lucanicoletti.glancetutorial.db.GlanceTutorialDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): GlanceTutorialDb =
        Room.databaseBuilder(context, GlanceTutorialDb::class.java, "app.db")
            .build()

    @Provides
    fun providesNotesDao(database: GlanceTutorialDb) = database.notesDao()
}
