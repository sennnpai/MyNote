package com.example.sevenproject.presentation.addNote

import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.domain.usecase.CreateNoteUseCase
import com.example.sevenproject.domain.usecase.UpdateNoteUseCase
import com.example.sevenproject.presentation.base.BaseViewModel
import com.example.sevenproject.presentation.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val  createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): BaseViewModel() {


    private val _noteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val noteState = _noteState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun insertAllNotes(note: Note) {
        createNoteUseCase(note).collectFlow(_noteState)
    }

    fun updateNotes(note: Note) {
        updateNoteUseCase(note).collectFlow(_updateNoteState)
    }

}