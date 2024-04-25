package ru.moneyTracker

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import ru.moneyTracker.features.login.configureLoginRouting
import ru.moneyTracker.features.login.configureRegisterRouting
import ru.moneyTracker.features.login.configureTransactionRouting
import ru.moneyTracker.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureTransactionRouting()
}
