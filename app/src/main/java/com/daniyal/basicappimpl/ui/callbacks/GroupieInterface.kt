package com.daniyal.basicappimpl.ui.callbacks

interface GroupieInterface<in C> {

    fun invokeSingleItemClick(item: C, position: Int)

}