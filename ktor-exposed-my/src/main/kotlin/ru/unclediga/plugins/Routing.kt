package ru.unclediga.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import ru.unclediga.dao.dao

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/all") {
            call.respondText(dao.allArticles().toString())
        }
        get("/{id}") {
            val id = call.parameters.getOrFail<Int>("id")
            call.respondText(dao.article(id).toString())
        }
        post {
            val parameters = call.receiveParameters()
            val title = parameters.getOrFail("title")
            val body = parameters.getOrFail("body")
            val article = dao.newArticle(title, body)
            println("->  ${article?.title} -> id[${article?.id}] ")
            println("/${article?.id}")
            call.respondRedirect("/${article?.id}")
        }
        post("/{id}/upd") {
            val id = call.parameters.getOrFail<Int>("id")
            val parameters = call.receiveParameters()
            val title = parameters.getOrFail("title")
            val body = parameters.getOrFail("body")
            dao.editArticle(id, title, body)
            call.respondRedirect("/all")
        }
        post("/{id}/del") {
            val id = call.parameters.getOrFail<Int>("id")
            val article = dao.deleteArticle(id)
            call.respondRedirect("/all")
        }
    }
}
