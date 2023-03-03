package com.example.sevenproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenproject.R
import com.example.sevenproject.data.repo.NoteRepositoryImpl
import com.example.sevenproject.domain.usecase.CreateNoteUseCase
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}