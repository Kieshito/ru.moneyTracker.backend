package ru.moneyTracker.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.moneyTracker.cache.InMemoryCache
import ru.moneyTracker.cache.TokenCache
import ru.moneyTracker.features.register.RegisterReceiveRemote
import ru.moneyTracker.features.register.RegisterResponseRemote
import ru.moneyTracker.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val receive = call.receive(RegisterReceiveRemote::class)
            if (!receive.email.isValidEmail()){
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }

            if (InMemoryCache.userList.map { it.login }.contains(receive.login)){
                call.respond(HttpStatusCode.Conflict, "User is already registered")
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.userList.add(receive)
            InMemoryCache.token.add(TokenCache(login = receive.login, token = token))


            call.respond(RegisterResponseRemote(token = token))
        }
    }
}