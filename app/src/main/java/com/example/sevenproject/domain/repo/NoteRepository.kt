package com.example.sevenproject.domain.repo

import com.example.sevenproject.domain.model.Note

interface NoteRepository {

    fun insertNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(note: Note)
    fun getAllNotes(): List<Note>
}