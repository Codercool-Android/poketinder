package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.databinding.ActivityDetailPokemonBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.bumptech.glide.Glide

class DetailPokemonActivity :
    BaseActivity<ActivityDetailPokemonBinding>(ActivityDetailPokemonBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemon = intent.getSerializableExtra("pokemon") as PokemonMock

        binding.tvName.text = pokemon.name
        binding.tvNameBackground.text = pokemon.name

        val posFormatted = "%03d".format(pokemon.position)
        val position = "#$posFormatted"
        binding.tvPosition.text = position

        Glide
            .with(this)
            .load(pokemon.img)
            .into(binding.ivPokemonLogo)

        // Evaluate if pokemon is legendary
        binding.ivLegendary.visibility = if (pokemon.legendary) View.VISIBLE else View.GONE

/*        val fade = Fade()

        with(fade) {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.enterTransition = fade
        window.exitTransition = fade*/

        binding.ivPokemonLogo.setOnClickListener {
            val intent = Intent(this, PokemonLargeScreenActivity::class.java)

            intent.putExtra("url", pokemon.img)

            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,
                    binding.ivPokemonLogo, "example_transition")

            startActivity(intent, options.toBundle())
        }


    }
}