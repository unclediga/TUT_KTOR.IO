package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/k") {
            call.respondText("Hello World /k!")
        }
        get("/kk") {
            call.respondText("Hello World /kk!")
        }
        get("/ktor") {
            call.respondText("Hello from /ktor!")
        }
        get("ktor") {
            call.respondText("Hello from ktor!")
        }
        get("/ktor/ktor2") {
            call.respondText("Hello from /ktor/ktor2!")
        }
        get("ktor2") {
            call.respondText("Hello from ktor2!")
        }
        get("/ktor2") {
            call.respondText("Hello from /ktor2!")
        }
        get("/ktor2/ktor3") {
            call.respondText("Hello from /ktor2/ktor3!")
        }

        get("/kk/ktor2") {
            call.respondText("Hello from /kk/ktor2!")
        }
        get("/kk/ktor2/ktor3") {
            call.respondText("Hello from /kk/ktor2/ktor3!")
        }
    }
}
