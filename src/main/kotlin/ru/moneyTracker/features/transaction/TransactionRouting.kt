package ru.moneyTracker.features.login


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.moneyTracker.cache.InMemoryCache
import ru.moneyTracker.features.transaction.TransactionReceiveRemote
import ru.moneyTracker.utils.isValid


fun Application.configureTransactionRouting() {
    routing {
        post("/Transaction") {
            val receive = call.receive(TransactionReceiveRemote::class)

            if (!receive.transaction.isValid()){
                call.respond(HttpStatusCode.BadRequest, "Not a valid transaction")
            }

            if (!InMemoryCache.token.map { it.token }.contains(receive.token)) {
                call.respond(HttpStatusCode.BadRequest, "Not a valid token")
            }

            for (tokenEntry in InMemoryCache.token) {
                if (tokenEntry.token == receive.token) {
                    val transList = InMemoryCache.transactions.get(tokenEntry.login)
                    transList?.add(receive.transaction)
                    if (transList != null) {
                        InMemoryCache.transactions.put(tokenEntry.login, transList)
                    }
                }
            }
            return@post
        }

    }
}