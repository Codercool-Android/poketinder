package com.abeltarazona.poketinder.domain.interactors.implementation

import com.zygne.zygnearchitecture.domain.executor.base.Executor
import com.zygne.zygnearchitecture.domain.executor.base.MainThread
import com.zygne.zygnearchitecture.domain.interactors.base.AbstractInteractor

/**
 * Created by AbelTarazona on 19/08/2021
 */
abstract class BaseInteractorImpl(executor: Executor, mainThread: MainThread)
    : AbstractInteractor(executor, mainThread) {

        // Logger de inicio-fin de llamada
        // Logger de clase a la que se ha llamado

}