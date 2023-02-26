package com.example.sevenproject.data.mapper

import com.example.sevenproject.data.model.NoteEntity
import com.example.sevenproject.domain.model.Note

fun Note.toNoteEntity() = NoteEntity(
    id, title, description, createdAt
)

fun NoteEntity.toNote() = Note(
    id, title, description, createdAt
)