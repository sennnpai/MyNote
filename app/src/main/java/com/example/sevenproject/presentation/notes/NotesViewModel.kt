package com.example.sevenproject.presentation.notes

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.usecase.DeleteNoteUseCase
import com.example.sevenproject.domain.usecase.GetAllUseCase
import com.example.sevenproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _noteState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val noteState = _noteState.asStateFlow()

    private val _deleteNoteSate = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val deleteNoteState = _deleteNoteSate.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() {
        getAllUseCase().collectFlow(_noteState)
    }

    fun deleteNotes(note: Note) {
        deleteNoteUseCase(note).collectFlow(_deleteNoteSate)

    }
}