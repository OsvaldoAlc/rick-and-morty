package com.example.feature.home.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.home.databinding.CharacterCardItemBinding
import com.example.model.RMCharacter

class RMCharactersAdapter(private val onItemClicked: (RMCharacter) -> Unit) :
    ListAdapter<RMCharacter, RMCharactersAdapter.RMCharactersViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class RMCharactersViewHolder(private var binding: CharacterCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rMCharacter: RMCharacter, context: Context) {
            binding.characterName.text = rMCharacter.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RMCharactersViewHolder {
        context = parent.context
        return RMCharactersViewHolder(
            CharacterCardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RMCharactersViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RMCharacter>() {
            override fun areItemsTheSame(oldItem: RMCharacter, newItem: RMCharacter): Boolean {
                return (oldItem.name == newItem.name)
            }

            override fun areContentsTheSame(oldItem: RMCharacter, newItem: RMCharacter): Boolean {
                return oldItem == newItem
            }
        }
    }
}
