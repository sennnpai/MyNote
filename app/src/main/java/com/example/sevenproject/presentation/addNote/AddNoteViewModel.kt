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

    fun insertAllNotes(title:String, desc:String) {
        if(title.isNotEmpty() && desc.isNotEmpty()){
            createNoteUseCase(Note(
                title = title,
                description = desc,
                createdAt = System.currentTimeMillis()
            )).collectFlow(_noteState)
        }else{
            _noteState.value = UiState.Error("Title is Empty")
        }

    }

    fun updateNotes(id:Int, title:String, desc:String) {
        if(id != null && title.isNotEmpty() && desc.isNotEmpty()){
            updateNoteUseCase(Note(
                id = id,
                title = title,
                description = desc,
                createdAt = System.currentTimeMillis()
            )).collectFlow(_updateNoteState)
        }else{
            _updateNoteState.value = UiState.Error("Title is Empty")
        }

    }

}