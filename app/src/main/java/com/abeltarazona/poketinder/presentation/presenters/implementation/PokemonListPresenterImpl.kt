package com.abeltarazona.poketinder.presentation.presenters.implementation

import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.abeltarazona.poketinder.domain.interactors.implementation.PokemonListInteractorImpl
import com.abeltarazona.poketinder.domain.interactors.interfaces.PokemonListInteractor
import com.abeltarazona.poketinder.presentation.presenters.interfaces.PokemonListPresenter
import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread
import com.zygne.zygnearchitecture.presentation.presenters.base.AbstractPresenter

/**
 * Created by AbelTarazona on 24/08/2021
 */
class PokemonListPresenterImpl(
    val callback: PokemonListPresenter.Callback,
    executor: Executor,
    mainThread: MainThread
) : AbstractPresenter(executor, mainThread), PokemonListInteractor.Callback, PokemonListPresenter {

    override fun getPokemonList() {
        callback.showProgress()
        val pokemonListInteractor = PokemonListInteractorImpl(
            this,
             executor, mainThread
        )
        pokemonListInteractor.execute()
    }

    override fun onGetPokemonListSuccess(list: List<PokemonListResponse.Pokemon>) {
        callback.hideProgress()
        callback.onPokemonListSuccess(list)
    }

    override fun onGeneralError(error: String) {
        callback.hideProgress()
        callback.onGeneralError(error)
    }

}