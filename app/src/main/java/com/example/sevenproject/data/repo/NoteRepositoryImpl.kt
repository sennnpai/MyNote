package com.example.sevenproject.data.repo

import com.example.sevenproject.data.base.BaseRepository
import com.example.sevenproject.data.local.NoteDao
import com.example.sevenproject.data.mapper.toNote
import com.example.sevenproject.data.mapper.toNoteEntity
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository
import com.example.sevenproject.domain.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
    ) : NoteRepository, BaseRepository() {
    override fun insertNote(note: Note): Flow<ResultStatus<Unit>> = doRequest {
        noteDao.insertNote(note.toNoteEntity())
    }


    override fun updateNote(note: Note): Flow<ResultStatus<Unit>> = doRequest {
        noteDao.updateNote(note.toNoteEntity())
    }

    override fun deleteNote(note: Note): Flow<ResultStatus<Unit>> = doRequest {
        noteDao.deleteNote(note.toNoteEntity())
    }


    override fun getAllNotes(): Flow<ResultStatus<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }
}