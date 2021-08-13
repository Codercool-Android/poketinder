package com.abeltarazona.poketinder.presentation.ui.activities

import android.os.Bundle
import android.transition.Fade
import com.abeltarazona.poketinder.databinding.ActivityPokemonLargeScreenBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.bumptech.glide.Glide

class PokemonLargeScreenActivity :
    BaseActivity<ActivityPokemonLargeScreenBinding>(ActivityPokemonLargeScreenBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val urlInt = intent.getStringExtra("url")

/*        val fade = Fade()

        with(fade) {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.enterTransition = fade
        window.exitTransition = fade*/


        Glide
            .with(this)
            .load(urlInt)
            .into(binding.ivPokemonLogo)


    }
}