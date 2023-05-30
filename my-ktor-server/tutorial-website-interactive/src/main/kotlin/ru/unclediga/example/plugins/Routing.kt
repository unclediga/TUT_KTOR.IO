package ru.unclediga.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.util.*

import ru.unclediga.example.models.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondRedirect("articles")
        }
        route("articles") {
            get {
                // Show list of articles
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles)))
            }
            get("new") {
                // Show page with fields for creating a new article
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                // Save an article
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val newEntry = Article.newEntry(title, body)
                articles.add(newEntry)
                call.respondRedirect("/articles/${newEntry.id}")
            }
            get("{id}") {
                // Show an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to articles.find{it.id == id})))
            }
            get("{id}/edit") {
                // Show a page with fields for editing an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to articles.find{it.id == id})))
            }
            post("{id}") {
                // Update or delete an article
                val id:Int = call.parameters.getOrFail("id").toInt()
                val formParameters = call.receiveParameters()
                when(formParameters.getOrFail<String>("_action")){
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        val index = articles.indexOf(articles.find { it.id == id })
                        articles.get(index).title = title
                        articles.get(index).body = body
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        articles.removeIf { it.id == id }
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
        static("/static") {
            resources("static")
        }

    }
}
