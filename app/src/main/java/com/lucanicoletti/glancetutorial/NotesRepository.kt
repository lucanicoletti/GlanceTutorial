package com.lucanicoletti.glancetutorial

import com.lucanicoletti.glancetutorial.db.dao.NotesDao
import com.lucanicoletti.glancetutorial.db.model.Note
import com.lucanicoletti.glancetutorial.widget.GlanceTutorialWidgetRepository
import java.util.UUID
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao,
    private val glanceTutorialWidgetRepository: GlanceTutorialWidgetRepository
) {

    suspend fun addRandomNote() {
        val uuid = UUID.randomUUID().toString()
        val randomNote = Note(
            title = uuid,
        )
        notesDao.insertNote(randomNote)
        glanceTutorialWidgetRepository.noteInserted()
    }

    fun getNotes() = notesDao.getNotes()

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
        glanceTutorialWidgetRepository.noteDeleted()
    }
}