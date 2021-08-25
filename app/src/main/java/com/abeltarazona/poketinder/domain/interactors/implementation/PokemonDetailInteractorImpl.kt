package com.abeltarazona.poketinder.domain.interactors.implementation

import android.util.Log
import com.abeltarazona.poketinder.data.core.RestClient
import com.abeltarazona.poketinder.domain.interactors.interfaces.PokemonDetailInteractor
import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread

/**
 * Created by AbelTarazona on 24/08/2021
 */
class PokemonDetailInteractorImpl(
    val id: String,
    val callback: PokemonDetailInteractor.Callback,
    executor: Executor,
    mainThread: MainThread
) : BaseInteractorImpl(executor, mainThread) {
    override fun run() {
        Log.d(PokemonDetailInteractorImpl::class.simpleName, "Running pokemonDetail")

        try {
            val response = RestClient.getPokemonDetail(id)

            if (response != null) {
                mainThread.post { callback.getDetailPokemonSuccess(response) }
            } else {
                mainThread.post { callback.onGeneralError("Detalle vacío") }
            }
        } catch (e: Exception) {
            Log.d(PokemonDetailInteractorImpl::class.simpleName, e.message.toString())
            mainThread.post { callback.onGeneralError("Hubo un error general") }
        }
    }
}