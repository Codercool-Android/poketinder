package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.data.internal.Pokemon
import com.abeltarazona.poketinder.data.internal.PokemonDB
import com.abeltarazona.poketinder.databinding.ActivityDetailPokemonBinding
import com.abeltarazona.poketinder.presentation.ui.dialog.SuccesDialog
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.formatNumberTo3Digits
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPokemonActivity :
    BaseActivity<ActivityDetailPokemonBinding>(ActivityDetailPokemonBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemon = intent.getSerializableExtra("pokemon") as PokemonMock

        binding.tvName.text = pokemon.name
        binding.tvNameBackground.text = pokemon.name

        binding.tvPosition.text = formatNumberTo3Digits(pokemon.position)

        Glide
            .with(this)
            .load(pokemon.img)
            .into(binding.ivPokemonLogo)

        // Evaluate if pokemon is legendary
        binding.ivLegendary.visibility = if (pokemon.legendary) View.VISIBLE else View.GONE


        binding.ivPokemonLogo.setOnClickListener {
            val intent = Intent(this, PokemonLargeScreenActivity::class.java)

            intent.putExtra("url", pokemon.img)

            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                    this,
                    binding.ivPokemonLogo, "example_transition"
                )

            startActivity(intent, options.toBundle())
        }

        binding.btnSavePokemon.setOnClickListener {
            // Guardar pokemon en BD interna (Room)
            savePokemon(pokemon)
        }


    }

    private fun savePokemon(pokemon: PokemonMock) {



        GlobalScope.launch(Dispatchers.IO) {
            val db = PokemonDB.provideDB(this@DetailPokemonActivity)
            db.pokemonDao().insert(
                Pokemon(
                    name = pokemon.name,
                    isLegendary = pokemon.legendary,
                    image = pokemon.img
                )
            )
        }

        SuccesDialog(title = "Enhorabuena!", subtitle = "Pokemon guardado exitosamente")
            .show(this)
    }
}