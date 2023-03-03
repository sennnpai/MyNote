package com.example.sevenproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val description: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)