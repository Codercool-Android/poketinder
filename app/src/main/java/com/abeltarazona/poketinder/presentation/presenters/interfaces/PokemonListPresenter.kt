package com.abeltarazona.poketinder.presentation.presenters.interfaces

import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.abeltarazona.poketinder.domain.CommonErrors
import com.zygne.zygnearchitecture.presentation.views.base.BaseView

/**
 * Created by AbelTarazona on 24/08/2021
 */
interface PokemonListPresenter {

    interface Callback : BaseView, CommonErrors {
        fun onPokemonListSuccess(list: List<PokemonListResponse.Pokemon>)
    }

    fun getPokemonList()

}