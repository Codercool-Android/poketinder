package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.abeltarazona.poketinder.data.internal.Pokemon
import com.abeltarazona.poketinder.data.internal.PokemonDB
import com.abeltarazona.poketinder.databinding.ActivityDetailPokemonBinding
import com.abeltarazona.poketinder.presentation.presenters.implementation.PokemonDetailPresenterImpl
import com.abeltarazona.poketinder.presentation.presenters.implementation.PokemonListPresenterImpl
import com.abeltarazona.poketinder.presentation.presenters.interfaces.PokemonDetailPresenter
import com.abeltarazona.poketinder.presentation.ui.dialog.SuccesDialog
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.formatNumberTo3Digits
import com.bumptech.glide.Glide
import com.zygne.zygnearchitecture.domain.executor.implementation.ThreadExecutor
import com.zygne.zygnearchitecture.threads.AndroidThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPokemonActivity :
    BaseActivity<ActivityDetailPokemonBinding>(ActivityDetailPokemonBinding::inflate),
    PokemonDetailPresenter.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemon = intent.getSerializableExtra("pokemon") as PokemonListResponse.Pokemon

        binding.tvName.text = pokemon.name
        binding.tvNameBackground.text = pokemon.name

        binding.tvPosition.text = formatNumberTo3Digits(pokemon.getPokemonId().toInt())

        Glide
            .with(this)
            .load(pokemon.getPokemonImage())
            .into(binding.ivPokemonLogo)

        // Evaluate if pokemon is legendary
        //binding.ivLegendary.visibility = if (pokemon.legendary) View.VISIBLE else View.GONE


        binding.ivPokemonLogo.setOnClickListener {
            val intent = Intent(this, PokemonLargeScreenActivity::class.java)

            intent.putExtra("url", pokemon.getPokemonImage())

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

        fetchDetailPokemonById(pokemon.getPokemonId())

    }

    private fun fetchDetailPokemonById(pokemonId: String) {
        val pokemonDetailPresenter = PokemonDetailPresenterImpl(
            this,
            ThreadExecutor.getInstance(),
            AndroidThread.getInstance()
        )

        pokemonDetailPresenter.getDetailPokemonById(pokemonId)
    }

    private fun savePokemon(pokemon: PokemonListResponse.Pokemon) {


        GlobalScope.launch(Dispatchers.IO) {
            val db = PokemonDB.provideDB(this@DetailPokemonActivity)
            db.pokemonDao().insert(
                Pokemon(
                    name = pokemon.name,
                    image = pokemon.getPokemonImage(),
                    idPokemon = pokemon.getPokemonId()
                )
            )
        }

        SuccesDialog(title = "Enhorabuena!", subtitle = "Pokemon guardado exitosamente")
            .show(this)
    }

    override fun onGetDetailPokemonSuccess(response: PokemonDetailResponse) {

    }

    override fun showProgress() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar2.visibility = View.GONE
    }

    override fun onGeneralError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}