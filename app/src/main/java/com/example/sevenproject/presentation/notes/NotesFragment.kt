package com.example.sevenproject.presentation.notes

import android.app.AlertDialog
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenproject.R
import com.example.sevenproject.databinding.FragmentNotesBinding
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.presentation.base.BaseFragment
import com.example.sevenproject.presentation.extention.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment :
    BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private lateinit var adapter: NoteAdapter
    private var alertDialog: AlertDialog? = null
    override val viewModel: NotesViewModel by viewModels()

    override fun initialize() {
        adapter = NoteAdapter(this::onClick)
        binding.rvNotes.adapter = adapter
    }

    override fun listeners() {
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }

        val swipeToDelete = object : Swipe() {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val builder = AlertDialog.Builder(activity)
                builder.run {
                    setTitle("Удалить эту заметку")
                    setMessage("Ты точно хочешь удалить?")
                    setPositiveButton("Да") { _, _ ->
                        viewModel.deleteNotes(adapter.deleteItem(viewHolder.adapterPosition))

                    }
                    setNegativeButton("Нет") { _, _ ->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                }

                alertDialog = builder.create()
                alertDialog?.show()
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }


    override fun setupRequests() {
        viewModel.getAllNotes()
        viewModel.noteState.collectState(onLoading = {
            binding.noteProgress.isVisible = true
        }, onError = {
            binding.noteProgress.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.noteProgress.isVisible = false
            adapter.addItem(it)
        })

        viewModel.deleteNoteState.collectState(onLoading = {
            binding.noteProgress.isVisible = true
        }, onError = {
            binding.noteProgress.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.noteProgress.isVisible = false
            showToast(R.string.delete)
        })
    }

    private fun onClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(KEY, note)
        findNavController().navigate(R.id.addNoteFragment, bundle)

    }

    companion object {
        const val KEY = "key"
    }


}