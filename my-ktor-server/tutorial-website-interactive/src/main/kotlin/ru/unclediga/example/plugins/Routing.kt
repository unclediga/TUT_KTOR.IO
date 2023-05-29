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
            }  
            post {
	        // Save an article
            }  
            get("{id}") {
	        // Show an article
            }  
            get("{id}/edit") {
	        // Show a page with fields for editing an article
            }  
            post("{id}") {
	         // Update or delete an article
            }  
        }
    }
}
