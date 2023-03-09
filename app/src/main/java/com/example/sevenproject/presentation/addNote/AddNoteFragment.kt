package com.example.sevenproject.presentation.addNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sevenproject.databinding.FragmentAddNoteBinding
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.presentation.base.BaseFragment
import com.example.sevenproject.presentation.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment  : BaseFragment<AddNoteViewModel, FragmentAddNoteBinding>() {

    override val viewModel: AddNoteViewModel by viewModels()
    override lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getSerializable(NotesFragment.KEY) as Note?

        if (arguments != null){
            binding.edTitle.setText(note?.title)
            binding.edDes.setText(note?.description)
        }

        binding.btnSaveNote.setOnClickListener{
            if(arguments != null) {
                viewModel.updateNotes(
                    Note(
                        id = note?.id!!,
                        title = binding.edTitle.text.toString(),
                        description = binding.edDes.text.toString(),
                        createdAt = 0
                    )
                )
            }else{
                viewModel.insertAllNotes(Note(

                    title = binding.edTitle.text.toString(),
                    description = binding.edDes.text.toString(),
                    createdAt = 0
                ))
            }
            findNavController().navigateUp()
        }
    }



    override fun setupRequests() {
        viewModel.noteState.collectState(onLoading = {
            Log.d("ololo", "AddNote: Loading")
        }, onError = {
            Log.d("ololo", "AddNote: Error")
        }, onSuccess = {
            Log.d("ololo", "AddNote: Success $it")

        })

        viewModel.updateNoteState.collectState(onLoading = {
            Log.d("ololo", "AddNote: Loading")
        }, onError = {
            Log.d("ololo", "AddNote: Error")
        }, onSuccess = {
            Log.d("ololo", "AddNote: Success $it")

        })

    }

}