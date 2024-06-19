package com.lucanicoletti.glancetutorial.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucanicoletti.glancetutorial.db.dao.NotesDao
import com.lucanicoletti.glancetutorial.db.model.Note

@Database(entities = [Note::class], version = 1)
abstract class GlanceTutorialDb : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}