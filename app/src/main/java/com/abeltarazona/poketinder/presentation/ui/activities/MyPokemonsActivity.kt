package com.abeltarazona.poketinder.presentation.ui.activities

import android.os.Bundle
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.data.internal.Pokemon
import com.abeltarazona.poketinder.data.internal.PokemonDB
import com.abeltarazona.poketinder.databinding.ActivityMyPokemonsBinding
import com.abeltarazona.poketinder.presentation.ui.adapters.MyPokemonsAdapter
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.Mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyPokemonsActivity :
    BaseActivity<ActivityMyPokemonsBinding>(ActivityMyPokemonsBinding::inflate) {

    private var listPokemonsSaved = mutableListOf<Pokemon>()

    private val adapter = MyPokemonsAdapter(listPokemonsSaved)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.layBackButton.btnBackClose.setOnClickListener {
            finish()
        }

        //listPokemonsSaved.addAll(Mock().getPokemons())

        binding.rvPokemons.adapter = adapter

        getPokemonsFromRoom()

    }

    fun getPokemonsFromRoom() {
        GlobalScope.launch(Dispatchers.IO) {
            val db = PokemonDB.provideDB(this@MyPokemonsActivity)
            val list = db.pokemonDao().getAllPokemons()
            listPokemonsSaved.addAll(list)
        }

        adapter.notifyDataSetChanged()
    }

}