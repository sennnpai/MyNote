package com.example.sevenproject.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sevenproject.databinding.ItemNoteBinding
import com.example.sevenproject.domain.model.Note

class NoteAdapter(
    val click: (Note) -> Unit,
    private val viewModel: NotesViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var items = arrayListOf<Note>()

    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bindTo(item: Note) {
            binding.tvTitle.text = item.title
            binding.tvDes.text = item.description
            binding.tvDate.text = item.createdAt.toString()

        }
    }

    fun deleteItem(position: Int) {
        viewModel.deleteNotes(items.removeAt(position))
        notifyItemRemoved(position)
    }

    fun addItem(items: List<Note>) {
        this.items = items as ArrayList<Note>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindTo(items[position])
        holder.itemView.setOnClickListener {
            click(items[position])
        }

    }


}


