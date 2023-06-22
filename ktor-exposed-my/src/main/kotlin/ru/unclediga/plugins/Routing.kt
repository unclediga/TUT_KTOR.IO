package ru.unclediga.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/a") {
            call.respondText("Hello World from path /aa !")
        }
        get("/b") {
            call.respondText("Hello World from path /bb !")
        }
    }
}
