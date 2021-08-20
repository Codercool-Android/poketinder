package com.abeltarazona.poketinder.domain.interactors.interfaces

import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.abeltarazona.poketinder.domain.CommonErrors
import com.zygne.zygnearchitecture.domain.interactors.base.Interactor

/**
 * Created by AbelTarazona on 19/08/2021
 */
interface PokemonListInteractor : Interactor {

    interface Callback : CommonErrors {
        fun onGetPokemonListSuccess(list: List<PokemonListResponse.PokemonResult>)
    }

}