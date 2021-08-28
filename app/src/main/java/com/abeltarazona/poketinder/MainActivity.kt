package com.abeltarazona.poketinder

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.abeltarazona.poketinder.data.PokemonMock
import com.abeltarazona.poketinder.data.User
import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.abeltarazona.poketinder.databinding.ActivityMainBinding
import com.abeltarazona.poketinder.presentation.presenters.implementation.PokemonListPresenterImpl
import com.abeltarazona.poketinder.presentation.presenters.interfaces.PokemonListPresenter
import com.abeltarazona.poketinder.presentation.ui.activities.DetailPokemonActivity
import com.abeltarazona.poketinder.presentation.ui.activities.MyPokemonsActivity
import com.abeltarazona.poketinder.presentation.ui.adapters.PokemonAdapter
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.Mock
import com.yuyakaido.android.cardstackview.*
import com.zygne.zygnearchitecture.domain.executor.implementation.ThreadExecutor
import com.zygne.zygnearchitecture.threads.AndroidThread

class MainActivity :
    BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    CardStackListener, PokemonAdapter.Callback, PokemonListPresenter.Callback {

    private val listPokemon = mutableListOf<PokemonListResponse.Pokemon>()

    private val manager by lazy {CardStackLayoutManager(this, this)}
    private val adapter by lazy { PokemonAdapter(listPokemon, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getSerializableExtra("user") as User

        initializeTinderCard()

        binding.layBackButton.btnBackClose.setOnClickListener {
            openCloseDialog()
        }

        binding.btnMyPokemons.setOnClickListener {
            startActivity(Intent(this, MyPokemonsActivity::class.java))
        }

        binding.floatingActionButton.setOnClickListener {
            // Rewind
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            binding.rvTinderPokemon.rewind()
        }

        binding.floatingActionButton2.setOnClickListener {
            // Skip
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        binding.floatingActionButton3.setOnClickListener {
            // Like
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        fetchPokemonApi()

    }

    private fun fetchPokemonApi() {
        val pokemonListPresenter = PokemonListPresenterImpl(
            this,
            ThreadExecutor.getInstance(),
            AndroidThread.getInstance()
        )

        pokemonListPresenter.getPokemonList()
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

    override fun onClickPokemonInformation(pokemon: PokemonListResponse.Pokemon) {
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

    // Card

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

    // Api

    override fun onPokemonListSuccess(list: List<PokemonListResponse.Pokemon>) {
        listPokemon.addAll(list)
        adapter.notifyDataSetChanged()

        binding.floatingActionButton.visibility = View.VISIBLE
        binding.floatingActionButton2.visibility = View.VISIBLE
        binding.floatingActionButton3.visibility = View.VISIBLE
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onGeneralError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}