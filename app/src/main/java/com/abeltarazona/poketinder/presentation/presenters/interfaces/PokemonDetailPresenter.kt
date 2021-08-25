package com.abeltarazona.poketinder.presentation.presenters.interfaces

import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.domain.CommonErrors
import com.zygne.zygnearchitecture.presentation.views.base.BaseView

/**
 * Created by AbelTarazona on 24/08/2021
 */
interface PokemonDetailPresenter {

    interface Callback : BaseView, CommonErrors {
        fun onGetDetailPokemonSuccess(response: PokemonDetailResponse)
    }

    fun getDetailPokemonById(id: String)

}