package com.abeltarazona.poketinder.presentation.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.data.internal.Pokemon
import com.abeltarazona.poketinder.databinding.ItemPokemonSavedBinding
import com.abeltarazona.poketinder.presentation.utils.formatNumberTo3Digits
import com.abeltarazona.poketinder.presentation.utils.inflate
import com.bumptech.glide.Glide

/**
 * Created by AbelTarazona on 17/08/2021
 */
class MyPokemonsAdapter(val list: List<Pokemon>) :
    RecyclerView.Adapter<MyPokemonsAdapter.MyPokemonsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonsHolder {
        val view = parent.inflate(R.layout.item_pokemon_saved)
        return MyPokemonsHolder(view)
    }

    override fun onBindViewHolder(holder: MyPokemonsHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    class MyPokemonsHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPokemonSavedBinding.bind(view)

        fun bind(pokemon: Pokemon) {
            with(binding) {

                val position = adapterPosition + 1
                tvIndex.text = formatNumberTo3Digits(position)
                tvName.text = pokemon.name
                tvType.text = if (pokemon.isLegendary) "Legendary" else "Normal"
                Glide
                    .with(itemView)
                    .load(pokemon.image)
                    .into(binding.ivPokemonLogo)
            }
        }

    }
}