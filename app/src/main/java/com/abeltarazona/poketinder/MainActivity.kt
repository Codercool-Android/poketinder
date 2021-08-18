package com.abeltarazona.poketinder

import android.R
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.data.User
import com.abeltarazona.poketinder.databinding.ActivityMainBinding
import com.abeltarazona.poketinder.presentation.ui.activities.DetailPokemonActivity
import com.abeltarazona.poketinder.presentation.ui.activities.MyPokemonsActivity
import com.abeltarazona.poketinder.presentation.ui.adapters.PokemonAdapter
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.Mock
import com.yuyakaido.android.cardstackview.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    CardStackListener, PokemonAdapter.Callback {

    private val manager by lazy {CardStackLayoutManager(this, this)}
    private val adapter by lazy { PokemonAdapter(Mock().getPokemons(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getSerializableExtra("user") as User

        binding.layBackButton.btnBackClose.setOnClickListener {
            openCloseDialog()
        }

        binding.btnMyPokemons.setOnClickListener {
            startActivity(Intent(this, MyPokemonsActivity::class.java))
        }

        initializeTinderCard()


    }

    private fun openCloseDialog() {
        AlertDialog.Builder(this)
            .setTitle("¿Está seguro de cerrar la app?")
            .setMessage("¡Tus matchs te extrañarán!")
            .setPositiveButton("Sí"
            ) { _, _ ->
                finish()
            }
            .setNegativeButton("¡Me quedo!", null)
            .show()
    }

    override fun onClickPokemonInformation(pokemon: PokemonMock) {
        // TODO: Open information screen
        val intent = Intent(this, DetailPokemonActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    private fun initializeTinderCard() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager
        binding.rvTinderPokemon.adapter = adapter
        binding.rvTinderPokemon.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
}