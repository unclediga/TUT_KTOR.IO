package ru.unclediga.example

import io.ktor.server.application.*
import ru.unclediga.example.dao.DatabaseFactory
import ru.unclediga.example.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    configureTemplating()
    configureRouting()
}
