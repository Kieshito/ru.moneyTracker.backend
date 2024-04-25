package ru.moneyTracker.cache

import ru.moneyTracker.features.register.RegisterReceiveRemote
import ru.moneyTracker.features.transaction.TransactionRemote

data class TokenCache(
    val login: String,
    val token: String,
)

object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()

    val transactions: MutableMap<String, MutableList<TransactionRemote> > = mutableMapOf() //login->transaction
}