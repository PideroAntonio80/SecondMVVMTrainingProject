package com.example.secondmvvmtrainingproject.domain.usecase.base

import java.util.*

open class BaseUseCase {
    /**
     * The name of this interactor. Note that every new instance **will** hold a
     * brand new name unless [interactorName] is overwritten.
     */
    private val name: String = UUID.randomUUID().toString()
    /**
     * Returns this interactos unique name. Note that **every new instance will have a
     * brand new name**. If a static name is required to be held among instances then the implementer
     * must overwrite this method and provide an unique name for the interactor.
     */
    open fun useCaseName(): String = this.name
}