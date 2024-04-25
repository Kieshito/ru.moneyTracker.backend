package ru.moneyTracker.features.login

import kotlinx.serialization.Serializable

@Serializable
class LoginReceiveRemote(
    val login: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String //jvt token почитать про сложную схему (в кторе есть)
)
