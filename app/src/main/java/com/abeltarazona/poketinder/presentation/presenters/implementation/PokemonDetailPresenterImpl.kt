package com.abeltarazona.poketinder.presentation.presenters.implementation

import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.domain.interactors.implementation.PokemonDetailInteractorImpl
import com.abeltarazona.poketinder.domain.interactors.interfaces.PokemonDetailInteractor
import com.abeltarazona.poketinder.presentation.presenters.interfaces.PokemonDetailPresenter
import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread
import com.zygne.zygnearchitecture.presentation.presenters.base.AbstractPresenter

/**
 * Created by AbelTarazona on 24/08/2021
 */
class PokemonDetailPresenterImpl(
    val callback: PokemonDetailPresenter.Callback,
    executor: Executor,
    mainThread: MainThread
) : AbstractPresenter(executor, mainThread),
    PokemonDetailInteractor.Callback, PokemonDetailPresenter {

    override fun getDetailPokemonById(id: String) {
        callback.showProgress()
        val detailInteractor = PokemonDetailInteractorImpl(
            id,
            this,
            executor, mainThread
        )
        detailInteractor.execute()
    }

    override fun getDetailPokemonSuccess(response: PokemonDetailResponse) {
        callback.hideProgress()
        callback.onGetDetailPokemonSuccess(response)
    }

    override fun onGeneralError(error: String) {
        callback.hideProgress()
        callback.onGeneralError(error)
    }
}