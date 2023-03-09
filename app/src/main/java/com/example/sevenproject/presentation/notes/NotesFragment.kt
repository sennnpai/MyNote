package com.example.sevenproject.presentation.notes

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenproject.R
import com.example.sevenproject.databinding.FragmentNotesBinding
import com.example.sevenproject.domain.model.Note
import com.example.sevenproject.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>() {

    private lateinit var adapter: NoteAdapter
    private var alertDialog: AlertDialog? = null
    override val viewModel: NotesViewModel by viewModels()
    override lateinit var binding: FragmentNotesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNotes()
        adapter = NoteAdapter(this::onClick, viewModel)
        binding.rvNotes.adapter = adapter


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
                        adapter.deleteItem(viewHolder.adapterPosition)

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
        viewModel.noteState.collectState(onLoading = {
            Log.d("ololo", "Note: Loading")
        }, onError = {
            Log.d("ololo", "Note: Error")
        }, onSuccess = {
            Log.d("ololo", "Note: Success $it")
            adapter.submitList(it)
        })

        viewModel.deleteNoteState.collectState(onLoading = {
            Log.d("ololo", "Note: Loading")
        }, onError = {
            Log.d("ololo", "Note: Error")
        }, onSuccess = {
            Log.d("ololo", "Note: Success $it")
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