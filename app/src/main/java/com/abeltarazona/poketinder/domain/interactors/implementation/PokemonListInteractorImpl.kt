package com.abeltarazona.poketinder.domain.interactors.implementation

import android.util.Log
import com.abeltarazona.poketinder.data.core.RestClient
import com.abeltarazona.poketinder.domain.interactors.interfaces.PokemonListInteractor
import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread

/**
 * Created by AbelTarazona on 19/08/2021
 */
class PokemonListInteractorImpl(
    val callback: PokemonListInteractor.Callback,
    executor: Executor,
    mainThread: MainThread
) : BaseInteractorImpl(executor, mainThread) {


    override fun run() {
        Log.d(PokemonListInteractorImpl::class.simpleName, "Running pokemonList")

        try {
            val response = RestClient.getPokemonList()

            if (response != null) {
                mainThread.post { callback.onGetPokemonListSuccess(response.results) }
            } else {
                mainThread.post { callback.onGeneralError("Lista vacía") }
            }

        } catch (e: Exception) {
            Log.d(PokemonListInteractorImpl::class.simpleName, e.message.toString())
            mainThread.post { callback.onGeneralError("Hubo un error general") }
        }

    }


}