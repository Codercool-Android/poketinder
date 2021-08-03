package com.abeltarazona.poketinder.presentation.presenters.implementation

import com.abeltarazona.poketinder.presentation.presenters.interfaces.MainIntroPresenter
import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread
import com.zygne.zygnearchitecture.presentation.presenters.base.AbstractPresenter

class MainIntroImpl(executor: Executor, mainThread: MainThread,
                    private val callback: MainIntroPresenter.Callback)
    : AbstractPresenter(executor, mainThread), MainIntroPresenter {

    override fun convertToWelcomeMessage(name: String) {
        val name2 = "$name Gutierrez"
        // ...
        // Lógica x
        // ...
        callback.onConvertToWelcomeMessageDone("Bienvenido amigo $name2")
    }

}