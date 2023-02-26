package com.example.sevenproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sevenproject.data.model.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getDao(): NoteDao
}