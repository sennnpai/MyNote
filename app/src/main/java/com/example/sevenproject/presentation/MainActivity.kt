package com.example.sevenproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.sevenproject.R
import com.example.sevenproject.data.repo.NoteRepositoryImpl
import com.example.sevenproject.databinding.ActivityMainBinding
import com.example.sevenproject.domain.usecase.CreateNoteUseCase
import com.example.sevenproject.presentation.notes.NotesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getAllNotes()


    }
}