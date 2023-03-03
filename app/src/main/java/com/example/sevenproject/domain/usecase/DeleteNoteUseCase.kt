package com.example.sevenproject.domain.usecase

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.repo.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun deleteNote(note: Note) =
        noteRepository.deleteNote(note)
}