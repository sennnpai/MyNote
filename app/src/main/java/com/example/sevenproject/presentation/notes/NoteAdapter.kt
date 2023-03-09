package com.example.sevenproject.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sevenproject.databinding.ItemNoteBinding
import com.example.sevenproject.domain.model.Note

class NoteAdapter(val click:(Note) -> Unit, private val viewModel: NotesViewModel) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(PLDDifferentCallBack) {


    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bindTo(item: Note) {
            binding.tvTitle.text = item.title
            binding.tvDes.text = item.description
            binding.tvDate.text = item.createdAt.toString()

        }
    }

    fun deleteItem(position: Int) {
        val item = getItem(position)
        viewModel.deleteNotes(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        holder.itemView.setOnClickListener{
            click(getItem(position))
        }

    }



    companion object{
        val PLDDifferentCallBack = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }

}


