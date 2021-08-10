package com.abeltarazona.poketinder.presentation.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.databinding.ItemPokemonBinding
import com.abeltarazona.poketinder.presentation.utils.inflate
import com.bumptech.glide.Glide

class PokemonAdapter(val list: List<PokemonMock>,
val callback: Callback) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_pokemon)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPokemonBinding.bind(view)

        fun bind(pokemon: PokemonMock) {
            with(binding) {
                root.setOnClickListener {
                    callback.onClick(pokemon)
                }
                tvName.text = pokemon.name
                Glide
                    .with(itemView)
                    .load(pokemon.img)
                    .into(binding.ivPokemon)
            }
        }

    }

    interface Callback {
        fun onClick(pokemon: PokemonMock)
    }
}