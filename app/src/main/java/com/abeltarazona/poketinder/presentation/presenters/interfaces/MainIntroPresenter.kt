package com.abeltarazona.poketinder.presentation.presenters.interfaces

interface MainIntroPresenter {

    // Acciones a realizar
    fun convertToWelcomeMessage(name: String)

    // Resultado
    interface Callback {
        fun onConvertToWelcomeMessageDone(message: String)
    }

}