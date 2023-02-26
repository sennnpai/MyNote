package com.example.sevenproject.data.repo

import com.example.sevenproject.data.local.NoteDao
import com.example.sevenproject.data.mapper.toNote
import com.example.sevenproject.data.mapper.toNoteEntity
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
    ) : NoteRepository {
    override fun insertNote(note: Note) {
        return noteDao.insertNote(note.toNoteEntity())
    }

    override fun updateNote(note: Note) {
        return noteDao.updateNote(note.toNoteEntity())
    }

    override fun deleteNote(note: Note) {
        return noteDao.deleteNote(note.toNoteEntity())
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toNote()}
    }
}