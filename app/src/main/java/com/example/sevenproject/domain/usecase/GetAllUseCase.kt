package com.example.sevenproject.domain.usecase

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository

class GetAllUseCase (
    private val noteRepository: NoteRepository
    ) {
        fun getAllNotes() =
            noteRepository.getAllNotes()
    }
