package com.lucanicoletti.glancetutorial

import com.lucanicoletti.glancetutorial.db.dao.NotesDao
import com.lucanicoletti.glancetutorial.db.model.Note
import java.util.UUID
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao,
) {

    suspend fun addRandomNote() {
        val uuid = UUID.randomUUID().toString()
        val randomNote = Note(
            title = uuid,
        )
        notesDao.insertNote(randomNote)
    }

    fun getNotes() = notesDao.getNotes()
}