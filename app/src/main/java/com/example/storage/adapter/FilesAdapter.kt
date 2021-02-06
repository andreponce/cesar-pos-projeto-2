package com.example.storage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.databinding.ItemFileBinding
import java.io.File

class FilesAdapter(private val onDeleteClick:(File) -> Unit) : ListAdapter<File, FilesAdapter.FileeHolder>(MyDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false);
        return FileeHolder(view)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }

    override fun onBindViewHolder(holder: FileeHolder, position: Int) {
        val file = currentList.get(position)
        with(holder) {
            binding.fileName.text = file.name

            binding.fileName.setOnClickListener {

            }

            binding.removeBt.setOnClickListener {
                onDeleteClick(file)
            }
        }
    }

    class FileeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemFileBinding.bind(itemView)

    }

    class MyDiff : DiffUtil.ItemCallback<File>(){
        override fun areItemsTheSame(oldItem: File, newItem: File): Boolean = oldItem==newItem
        override fun areContentsTheSame(oldItem: File, newItem: File): Boolean = oldItem.name==newItem.name
    }
}