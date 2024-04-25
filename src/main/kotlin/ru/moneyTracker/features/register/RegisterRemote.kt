package ru.moneyTracker.features.register

import kotlinx.serialization.Serializable

@Serializable
class RegisterReceiveRemote (
    val login: String,
    val email: String,
    val password: String
)

@Serializable
class RegisterResponseRemote(
    val token: String
)