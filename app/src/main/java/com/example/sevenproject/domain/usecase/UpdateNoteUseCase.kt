package com.example.sevenproject.domain.usecase

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun updateNote(note: Note) =
        noteRepository.updateNote(note)
}