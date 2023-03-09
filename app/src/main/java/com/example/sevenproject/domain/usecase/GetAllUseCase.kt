package com.example.sevenproject.domain.usecase

import com.example.sevenproject.domain.repo.NoteRepository
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val noteRepository: NoteRepository
    ) {
        operator fun invoke() =
            noteRepository.getAllNotes()
    }
