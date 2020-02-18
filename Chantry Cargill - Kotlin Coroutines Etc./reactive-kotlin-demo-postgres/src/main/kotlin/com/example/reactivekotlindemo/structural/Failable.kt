package com.example.reactivekotlindemo.structural

sealed class Failable<out L, out R> {
    data class Success<out L>(val success: L) : Failable<L, Nothing>()
    data class Failure<out R>(val failure: R) : Failable<Nothing, R>()

    fun <T> fold(successOp: (L) -> T, failureOp: (R) -> T): T {
        return when (this) {
            is Success -> successOp(this.success)
            is Failure -> failureOp(this.failure)
        }
    }
}