package ru.unclediga.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.unclediga.dao.dao

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/article/all") {
            call.respondText(dao.allArticles().toString())
        }
    }
}
