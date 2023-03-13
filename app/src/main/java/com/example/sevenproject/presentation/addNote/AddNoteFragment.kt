package com.example.sevenproject.presentation.addNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sevenproject.R
import com.example.sevenproject.databinding.FragmentAddNoteBinding
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.presentation.base.BaseFragment
import com.example.sevenproject.presentation.extention.showToast
import com.example.sevenproject.presentation.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : BaseFragment<AddNoteViewModel, FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    override val viewModel: AddNoteViewModel by viewModels()
    private val note by lazy { arguments?.getSerializable(NotesFragment.KEY) as Note? }


    override fun initialize() {
        if (arguments != null) {
            binding.edTitle.setText(note?.title)
            binding.edDes.setText(note?.description)
        }
    }

    override fun listeners() {
        with(binding) {
            btnSaveNote.setOnClickListener {
                if(arguments != null){
                    viewModel.updateNotes(
                        id = note?.id!!,
                        edTitle.text.toString(),
                        edDes.text.toString()
                    )
                }else {
                    viewModel.insertAllNotes(
                        edTitle.text.toString(),
                        edDes.text.toString()
                    )
                }

            }
        }


    }


    override fun setupRequests() {
        viewModel.updateNoteState.collectState(onLoading = {
            binding.addNoteProgress.isVisible = true
        }, onError = {
            binding.addNoteProgress.isVisible = false
            showToast(it)
        }, onSuccess = {
            showToast(R.string.updated)
            findNavController().navigateUp()

        })

        viewModel.noteState.collectState(onLoading = {
            binding.addNoteProgress.isVisible = true
        }, onError = {
            binding.addNoteProgress.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.addNoteProgress.isVisible = false
            showToast(R.string.insert)
            findNavController().navigateUp()
        })

    }

}