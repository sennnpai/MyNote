package com.example.sevenproject.data.repo

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
    ) : NoteRepository {
    override fun insertNote(note: Note): Flow<ResultStatus<Unit>> = flow {
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.insertNote(note.toNoteEntity())
            emit(ResultStatus.Success(data))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)


    override fun updateNote(note: Note): Flow<ResultStatus<Unit>> = flow {
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.updateNote(note.toNoteEntity())
            emit(ResultStatus.Success(data))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteNote(note: Note): Flow<ResultStatus<Unit>> = flow {
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.deleteNote(note.toNoteEntity())
            emit(ResultStatus.Success(data))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)


    override fun getAllNotes(): Flow<ResultStatus<List<Note>>> = flow {
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.getAllNotes().map { it.toNote() }
            emit(ResultStatus.Success(data))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}