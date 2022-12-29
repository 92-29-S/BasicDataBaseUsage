package com.example.basicdatabaseusage.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.basicdatabaseusage.databases.WordEntity


class WordListAdapter(diffCallback: DiffUtil.ItemCallback<WordEntity>) : androidx.recyclerview.widget.ListAdapter<WordEntity, WordViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current: WordEntity = getItem(position)
        holder.bind(current.getValue())
    }

    internal class WordDiff : DiffUtil.ItemCallback<WordEntity>() {
        override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem.getValue().equals(newItem.getValue())
        }
    }
}