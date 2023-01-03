package com.example.basicdatabaseusage.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicdatabaseusage.R
import com.example.basicdatabaseusage.databases.WordEntity


class WordListAdapter(diffCallback: DiffUtil.ItemCallback<WordEntity>) : androidx.recyclerview.widget.ListAdapter<WordEntity, WordListAdapter.WordViewHolder>(diffCallback) {
    class WordViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView

        init {
            wordItemView = itemView.findViewById(R.id.textView)
        }

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

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