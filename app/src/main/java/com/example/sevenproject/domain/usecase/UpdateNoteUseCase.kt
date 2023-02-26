package com.example.sevenproject.domain.usecase

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    fun updateNote(note: Note) =
        noteRepository.updateNote(note)
}