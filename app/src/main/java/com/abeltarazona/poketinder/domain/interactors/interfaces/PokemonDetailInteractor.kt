package com.abeltarazona.poketinder.domain.interactors.interfaces

import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.domain.CommonErrors
import com.zygne.zygnearchitecture.domain.interactors.base.Interactor

/**
 * Created by AbelTarazona on 24/08/2021
 */
interface PokemonDetailInteractor : Interactor {
    interface Callback : CommonErrors {
        fun getDetailPokemonSuccess(response: PokemonDetailResponse)
    }
}