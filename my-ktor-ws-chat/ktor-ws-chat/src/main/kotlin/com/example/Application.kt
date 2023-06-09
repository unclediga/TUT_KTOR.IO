package com.example

import com.example.db.DAOFacadeImpl
import com.example.db.initDatabase
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.time.Duration

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    initDatabase(this.environment.config)

    val dao = DAOFacadeImpl()

    routing {
        val connections = mutableSetOf<Connection>()

        webSocket("/chat") {
            val c = Connection(this)
            connections += c

            try {
                c.session.send("Welcome to the chat. ${connections.size} users are online")
                dao.getMessages().forEach {
                    c.session.send(it)
                }

                for (frame in incoming) {

                    frame as? Frame.Text ?: continue
                    val text = frame.readText()

                    val message = "${c.name} $text"

                    dao.saveMessage(message)

                    connections.forEach {
                        it.session.send(message)
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("${c.name} disconnected")
                connections -= c
            }

        }
    }
}
