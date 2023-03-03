package com.example.sevenproject.domain.repo

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun insertNote(note: Note) : Flow<ResultStatus<Unit>>
    fun updateNote(note: Note): Flow<ResultStatus<Unit>>
    fun deleteNote(note: Note): Flow<ResultStatus<Unit>>
    fun getAllNotes(): Flow<ResultStatus<List<Note>>>
}