package ru.moneyTracker.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.moneyTracker.cache.InMemoryCache
import ru.moneyTracker.cache.TokenCache
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val recieve = call.receive(LoginReceiveRemote::class)
            if (InMemoryCache.userList.map { it.login }.contains(recieve.login)) {
                val token = UUID.randomUUID().toString()
                InMemoryCache.token.add(TokenCache(login = recieve.login, token = token))
                call.respond(LoginResponseRemote(token = token))
                return@post
            }

            call.respond(HttpStatusCode.BadRequest, "User is not found")
        }
    }
}